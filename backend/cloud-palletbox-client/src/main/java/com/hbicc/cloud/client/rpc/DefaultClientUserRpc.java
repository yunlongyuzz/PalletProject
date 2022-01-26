package com.hbicc.cloud.client.rpc;
import com.hbicc.cloud.client.interceptor.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(value = "cloud-palletbox-service", configuration = FeignInterceptor.class)
public interface DefaultClientUserRpc {
    @PostMapping("/default_client_user/getList")
    String getList(@RequestBody  String json);
    @PostMapping("/default_client_user/getInfo")
    String getInfo(@RequestBody  String json);
    @PostMapping("/default_client_user/addInfo")
    String addInfo(@RequestBody  String json);
    @PostMapping("/default_client_user/delInfo")
    String delInfo(@RequestBody  String json);
    @PostMapping("/default_client_user/saveInfo")
    String saveInfo(@RequestBody  String json);
    @PostMapping("/default_client_user/getFieldParam")
    String getFieldParam(@RequestBody  String json);
    @PostMapping("/default_client_user/getAllList")
    String getAllList(@RequestBody  String json);
}
