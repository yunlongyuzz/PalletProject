package com.hbicc.cloud.admin.rpc;
import com.hbicc.cloud.admin.interceptor.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "cloud-palletbox-service", configuration = FeignInterceptor.class)
public interface DefaultAdminRpc {

    @PostMapping("/default_admin/getList")
    String getList(@RequestBody  String json);

    @PostMapping("/default_admin/getInfo")
    String getInfo(@RequestBody  String json);

    @PostMapping("/default_admin/addInfo")
    String addInfo(@RequestBody  String json);

/*
    @PostMapping("/default_admin/delInfo")
    String delInfo(@RequestBody  String json);
*/

    @PostMapping("/default_admin/saveInfo")
    String saveInfo(@RequestBody  String json);

    @PostMapping("/default_admin/getFieldParam")
    String getFieldParam(@RequestBody  String json);

    @PostMapping("/default_admin/getAllList")
    String getAllList(@RequestBody  String json);

}
