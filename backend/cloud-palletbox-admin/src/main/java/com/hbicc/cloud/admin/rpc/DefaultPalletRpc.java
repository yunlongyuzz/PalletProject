package com.hbicc.cloud.admin.rpc;
import com.hbicc.cloud.admin.interceptor.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(value = "cloud-palletbox-service", configuration = FeignInterceptor.class)
public interface DefaultPalletRpc {
    @PostMapping("/default_pallet/getList")
    String getList(@RequestBody  String json);
    @PostMapping("/default_pallet/getInfo")
    String getInfo(@RequestBody  String json);
    @PostMapping("/default_pallet/addInfo")
    String addInfo(@RequestBody  String json);
/*
    @PostMapping("/default_pallet/delInfo")
    String delInfo(@RequestBody  String json);
*/
    @PostMapping("/default_pallet/saveInfo")
    String saveInfo(@RequestBody  String json);
    @PostMapping("/default_pallet/getFieldParam")
    String getFieldParam(@RequestBody  String json);
    @PostMapping("/default_pallet/getAllList")
    String getAllList(@RequestBody  String json);
}
