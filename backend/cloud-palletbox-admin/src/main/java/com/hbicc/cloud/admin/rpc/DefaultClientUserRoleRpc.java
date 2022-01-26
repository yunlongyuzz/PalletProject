package com.hbicc.cloud.admin.rpc;
import com.hbicc.cloud.admin.interceptor.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(value = "cloud-palletbox-service", configuration = FeignInterceptor.class)
public interface DefaultClientUserRoleRpc {
    @PostMapping("/default_client_user_role/getList")
    String getList(@RequestBody  String json);
    @PostMapping("/default_client_user_role/getInfo")
    String getInfo(@RequestBody  String json);
    @PostMapping("/default_client_user_role/addInfo")
    String addInfo(@RequestBody  String json);
    @PostMapping("/default_client_user_role/delInfo")
    String delInfo(@RequestBody  String json);
    @PostMapping("/default_client_user_role/saveInfo")
    String saveInfo(@RequestBody  String json);
    @PostMapping("/default_client_user_role/getFieldParam")
    String getFieldParam(@RequestBody  String json);
    @PostMapping("/default_client_user_role/getAllList")
    String getAllList(@RequestBody  String json);
}
