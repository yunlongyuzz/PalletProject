package com.hbicc.cloud.client.rpc;
import com.hbicc.cloud.client.interceptor.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(value = "cloud-palletbox-service", configuration = FeignInterceptor.class)
public interface DefaultPalletLogRpc {
    @PostMapping("/default_pallet_log/getList")
    String getList(@RequestBody  String json);
    @PostMapping("/default_pallet_log/getInfo")
    String getInfo(@RequestBody  String json);
/*
    @PostMapping("/default_pallet_log/addInfo")
    String addInfo(@RequestBody  String json);
*/
/*
    @PostMapping("/default_pallet_log/delInfo")
    String delInfo(@RequestBody  String json);
*/
/*
    @PostMapping("/default_pallet_log/saveInfo")
    String saveInfo(@RequestBody  String json);
*/
    @PostMapping("/default_pallet_log/getFieldParam")
    String getFieldParam(@RequestBody  String json);
    @PostMapping("/default_pallet_log/getAllList")
    String getAllList(@RequestBody  String json);
}
