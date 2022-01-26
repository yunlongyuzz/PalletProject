package com.hbicc.cloud.client.controller;
import java.util.Map;
import com.hbicc.cloud.client.rpc.DefaultAdminRpc;
import com.hbicc.cloud.client.utils.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/default_admin")
public class DefaultAdminController {

    @Autowired
    DefaultAdminRpc defaultAdminRpc;


    @PostMapping("getList")
    public String getList(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultAdminRpc.getList(json);
    }
    @PostMapping("getInfo")
    public String getInfo(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultAdminRpc.getInfo(json);
    }
    @PostMapping("addInfo")
    public String addInfo(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultAdminRpc.addInfo(json);
    }
/*
    @PostMapping("delInfo")
    public String delInfo(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultAdminRpc.delInfo(json);
    }
*/
    @PostMapping("saveInfo")
    public String saveInfo(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultAdminRpc.saveInfo(json);
    }
    @PostMapping("getFieldParam")
    public String getFieldParam(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultAdminRpc.getFieldParam(json);
    }
/*
    @PostMapping("getAllList")
    public String getAllList(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultAdminRpc.getAllList(json);
    }
*/
}
