package com.hbicc.cloud.client.rpc;
import com.hbicc.cloud.client.interceptor.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(value = "cloud-palletbox-service", configuration = FeignInterceptor.class)
public interface DefaultCarRpc {
    @PostMapping("/default_car/getList")
    String getList(@RequestBody  String json);
    @PostMapping("/default_car/getInfo")
    String getInfo(@RequestBody  String json);
    @PostMapping("/default_car/addInfo")
    String addInfo(@RequestBody  String json);
    @PostMapping("/default_car/delInfo")
    String delInfo(@RequestBody  String json);
    @PostMapping("/default_car/saveInfo")
    String saveInfo(@RequestBody  String json);
    @PostMapping("/default_car/getFieldParam")
    String getFieldParam(@RequestBody  String json);
    @PostMapping("/default_car/getAllList")
    String getAllList(@RequestBody  String json);
}
