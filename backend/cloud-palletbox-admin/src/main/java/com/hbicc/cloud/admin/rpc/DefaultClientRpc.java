package com.hbicc.cloud.admin.rpc;
import com.hbicc.cloud.admin.interceptor.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(value = "cloud-palletbox-service", configuration = FeignInterceptor.class)
public interface DefaultClientRpc {
    @PostMapping("/default_client/getList")
    String getList(@RequestBody  String json);
    @PostMapping("/default_client/getInfo")
    String getInfo(@RequestBody  String json);
    @PostMapping("/default_client/addInfo")
    String addInfo(@RequestBody  String json);
/*
    @PostMapping("/default_client/delInfo")
    String delInfo(@RequestBody  String json);
*/
    @PostMapping("/default_client/saveInfo")
    String saveInfo(@RequestBody  String json);
    @PostMapping("/default_client/getFieldParam")
    String getFieldParam(@RequestBody  String json);
    @PostMapping("/default_client/getAllList")
    String getAllList(@RequestBody  String json);
}
