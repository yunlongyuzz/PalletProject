package com.hbicc.cloud.client.interceptor;
import java.util.Enumeration;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * feign拦截器
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        try {
            Object userInfoObj = request.getAttribute("user_info");
            if(userInfoObj != null){
                Map<String, Object> userInfo = (Map<String, Object>)userInfoObj;
                requestTemplate.header("user-type", userInfo.get("type").toString());
                requestTemplate.header("user-id", userInfo.get("id").toString());
                requestTemplate.header("client-id", userInfo.get("client_id").toString());
            }
        } catch (Exception e) {
        }
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                if (name.equals("x-token") || name.equals("content-type") || name.equals("accept")
                        || name.equals("x-real-ip")) {
                    String values = request.getHeader(name);
                    requestTemplate.header(name, values);
                }
            }
        }
    }


}
