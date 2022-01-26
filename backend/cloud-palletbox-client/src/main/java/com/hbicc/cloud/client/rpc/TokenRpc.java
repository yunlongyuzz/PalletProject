package com.hbicc.cloud.client.rpc;
import com.hbicc.cloud.client.interceptor.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
@FeignClient(value = "cloud-palletbox-service", configuration = FeignInterceptor.class)
public interface TokenRpc {
    @PostMapping("/token/client/get")
    String clientGet(@RequestBody  String json);
}
