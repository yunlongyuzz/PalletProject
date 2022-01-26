package com.hbicc.cloud.admin.rpc;
import com.hbicc.cloud.admin.interceptor.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
@FeignClient(value = "cloud-palletbox-service", configuration = FeignInterceptor.class)

public interface TokenRpc {
    @PostMapping("/token/admin/get")
    String adminGet(@RequestBody  String json);

}
