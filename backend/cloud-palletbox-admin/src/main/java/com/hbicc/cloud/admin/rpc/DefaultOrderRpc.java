package com.hbicc.cloud.admin.rpc;
import com.hbicc.cloud.admin.interceptor.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(value = "cloud-palletbox-service", configuration = FeignInterceptor.class)
public interface DefaultOrderRpc {


    @PostMapping("/default_order/getList")
    String getList(@RequestBody  String json);


    @PostMapping("/default_order/getInfo")
    String getInfo(@RequestBody  String json);
/*
    @PostMapping("/default_order/addInfo")
    String addInfo(@RequestBody  String json);
*/
/*
    @PostMapping("/default_order/delInfo")
    String delInfo(@RequestBody  String json);
*/
/*
    @PostMapping("/default_order/saveInfo")
    String saveInfo(@RequestBody  String json);
*/
    @PostMapping("/default_order/getFieldParam")
    String getFieldParam(@RequestBody  String json);
    @PostMapping("/default_order/getAllList")
    String getAllList(@RequestBody  String json);
}
