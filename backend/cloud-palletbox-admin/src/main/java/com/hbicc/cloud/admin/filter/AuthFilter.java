package com.hbicc.cloud.admin.filter;
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
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;

        //获取请求的URL地址
        String requestURI = request.getRequestURI();
        String[] requestURIArray = { "/token/admin/get" };

        //请求地址是不是包含在内，如果是，无需验证，如果不是，则需要验证
        if (ArrayUtil.contains(requestURIArray, requestURI)) {
            //放行，无需验证
            filterChain.doFilter(request, servletResponse);
        } else {
            //从请求头中获取token
            String token = request.getHeader("X-Token");
            if (null == token || token.isEmpty()) {
                this.error(servletResponse, -99, "需要提交token信息", null);
                return;
            } else {
                //进行验证
                try {
                    Map<String, Object> userInfo = null;
                    //解密token变成map类型的UserInfo
                    userInfo = JwtUtil.parseJWT(token);

                    //获取这个token的id
                    String userId = userInfo.get("id").toString();

                    if (StrUtil.isNotEmpty(userId)) {

                        //将token信息放进去request请求中
                        request.setAttribute("user_info", userInfo);

                        //如果不是admin类型的，报错
                        if(!userInfo.get("type").equals("admin")){
                            this.error(servletResponse, -99, "没有登录！", null);
                            return;
                        }
                        //是admin类型的，验证正确，放行
                        filterChain.doFilter(request, servletResponse);
                    } else {
                        //token里面的id为空
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
