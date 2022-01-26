package com.hbicc.cloud.upload.rpc;
import com.hbicc.cloud.upload.interceptor.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
// import org.springframework.web.bind.annotation.*;
@FeignClient(value = "cloud-com.hbicc.cloud-service", configuration = FeignInterceptor.class)
public interface TokenRpc {
    // @PostMapping("/token/upload/get")
    // String uploadGet(@RequestBody  String json);
}
