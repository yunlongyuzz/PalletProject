package com.hbicc.cloud.admin.controller;
import java.util.Map;
import com.hbicc.cloud.admin.rpc.DefaultClientRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/default_client")
public class DefaultClientController {
    @Autowired
    DefaultClientRpc defaultClientRpc;
    @PostMapping("getList")
    public String getList(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultClientRpc.getList(json);
    }
    @PostMapping("getInfo")
    public String getInfo(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultClientRpc.getInfo(json);
    }
    @PostMapping("addInfo")
    public String addInfo(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultClientRpc.addInfo(json);
    }
/*
    @PostMapping("delInfo")
    public String delInfo(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultClientRpc.delInfo(json);
    }
*/
    @PostMapping("saveInfo")
    public String saveInfo(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultClientRpc.saveInfo(json);
    }
    @PostMapping("getFieldParam")
    public String getFieldParam(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultClientRpc.getFieldParam(json);
    }
/*
    @PostMapping("getAllList")
    public String getAllList(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultClientRpc.getAllList(json);
    }
*/
}
