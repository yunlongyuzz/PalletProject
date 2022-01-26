package com.hbicc.cloud.client.rpc;
import com.hbicc.cloud.client.interceptor.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(value = "cloud-palletbox-service", configuration = FeignInterceptor.class)
public interface DefaultClientUserNodeRpc {
    @PostMapping("/default_client_user_node/getList")
    String getList(@RequestBody  String json);
    @PostMapping("/default_client_user_node/getInfo")
    String getInfo(@RequestBody  String json);
    @PostMapping("/default_client_user_node/addInfo")
    String addInfo(@RequestBody  String json);
    @PostMapping("/default_client_user_node/delInfo")
    String delInfo(@RequestBody  String json);
    @PostMapping("/default_client_user_node/saveInfo")
    String saveInfo(@RequestBody  String json);
    @PostMapping("/default_client_user_node/getFieldParam")
    String getFieldParam(@RequestBody  String json);
    @PostMapping("/default_client_user_node/getAllList")
    String getAllList(@RequestBody  String json);
}
