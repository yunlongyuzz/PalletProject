package com.hbicc.cloud.admin.rpc;
import com.hbicc.cloud.admin.interceptor.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(value = "cloud-palletbox-service", configuration = FeignInterceptor.class)
public interface DefaultPayRuleRpc {
    @PostMapping("/default_pay_rule/getList")
    String getList(@RequestBody  String json);
    @PostMapping("/default_pay_rule/getInfo")
    String getInfo(@RequestBody  String json);
    @PostMapping("/default_pay_rule/addInfo")
    String addInfo(@RequestBody  String json);
    @PostMapping("/default_pay_rule/delInfo")
    String delInfo(@RequestBody  String json);
    @PostMapping("/default_pay_rule/saveInfo")
    String saveInfo(@RequestBody  String json);
    @PostMapping("/default_pay_rule/getFieldParam")
    String getFieldParam(@RequestBody  String json);
    @PostMapping("/default_pay_rule/getAllList")
    String getAllList(@RequestBody  String json);
}
