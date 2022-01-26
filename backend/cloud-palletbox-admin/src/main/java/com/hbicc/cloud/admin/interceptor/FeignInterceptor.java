package com.hbicc.cloud.admin.interceptor;
import java.util.Enumeration;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import feign.RequestInterceptor;
import feign.RequestTemplate;


@Configuration
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();


        try {
            Object userInfoObj = request.getAttribute("user_info");
            //如果有token，就把userinfo里面的信息设置到请求头里带过去
            if (userInfoObj != null) {
                Map<String, Object> userInfo = (Map<String, Object>) userInfoObj;
                requestTemplate.header("user-type", userInfo.get("type").toString());
                requestTemplate.header("user-id", userInfo.get("id").toString());
                requestTemplate.header("client-id", "client-id");
            }
        } catch (Exception e) {
        }

        //获取请求头的名称
        Enumeration<String> headerNames = request.getHeaderNames();


        if (headerNames != null) {
            //如果有token，那么请求头一定不为空，有token的情况下

            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();

                //如果有token，看看有没有以下请求头，如果有，就把token信息带过去
                if (name.equals("x-token") || name.equals("content-type") || name.equals("accept")
                        || name.equals("x-real-ip")) {

                    String values = request.getHeader(name);

                    requestTemplate.header(name, values);
                }
            }

        }


    }

}
