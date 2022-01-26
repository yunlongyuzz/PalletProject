package com.hbicc.cloud.upload.interceptor;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * feign的拦截器
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            // while (headerNames.hasMoreElements()) {
            //     String name = headerNames.nextElement();
            //     if(name.equals("x-token") || name.equals("content-type") || name.equals("accept")){
            //         String values = request.getHeader(name);
            //         requestTemplate.header(name, values);
            //     }
            // }
        }
    }


}
