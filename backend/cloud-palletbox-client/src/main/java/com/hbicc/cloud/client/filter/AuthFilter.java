package com.hbicc.cloud.client.filter;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.hbicc.cloud.common.utils.JwtUtil;
import com.hbicc.cloud.common.utils.ResponseUtil;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@WebFilter(filterName = "authFilter", urlPatterns = "/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Console.log("AuthFilter Init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        String[] requestURIArray = { "/token/client/get" };

        //请求地址是不是包含在内，如果是，无需验证，如果不是，则需要验证
        if (ArrayUtil.contains(requestURIArray, requestURI)) {
            // 无须验证token的路径
            filterChain.doFilter(request, servletResponse);
        } else {
            // 验证token的有效性
            String token = request.getHeader("X-Token");
            if (null == token || token.isEmpty()) {
                this.error(servletResponse, -99, "需要提交token信息", null);
                return;
            } else {
                try {
                    Map<String, Object> userInfo = null;
                    userInfo = JwtUtil.parseJWT(token);
                    String userId = userInfo.get("id").toString();
                    if (StrUtil.isNotEmpty(userId)) {
                        request.setAttribute("user_info", userInfo);
                        if(!userInfo.get("type").equals("client")){
                            this.error(servletResponse, -99, "没有登录！", null);
                            return;
                        }
                        filterChain.doFilter(request, servletResponse);
                    } else {
                        this.error(servletResponse, -99, "提交的token不正确", null);
                        return;
                    }
                } catch (Exception e) {
                    this.error(servletResponse, -99, e.getMessage(), null);
                    return;
                }
            }
        }
    }

    @Override
    public void destroy() {
        Console.log("AuthFilter Destroy...");
    }

    private void error(ServletResponse servletResponse, Integer code, String message, Object obj) throws IOException {
        servletResponse.setContentType("application/json;charset=utf-8");
        servletResponse.getWriter().write(JSONUtil.toJsonStr(ResponseUtil.error(code, message, obj)));
    }

}
