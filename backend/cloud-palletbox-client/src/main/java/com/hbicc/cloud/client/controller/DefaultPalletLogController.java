package com.hbicc.cloud.client.controller;
import java.util.Map;
import com.hbicc.cloud.client.rpc.DefaultPalletLogRpc;
import com.hbicc.cloud.client.utils.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/default_pallet_log")
public class DefaultPalletLogController {
    @Autowired
    DefaultPalletLogRpc defaultPalletLogRpc;
    @PostMapping("getList")
    public String getList(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultPalletLogRpc.getList(json);
    }
    @PostMapping("getInfo")
    public String getInfo(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultPalletLogRpc.getInfo(json);
    }
/*
    @PostMapping("addInfo")
    public String addInfo(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultPalletLogRpc.addInfo(json);
    }
*/
/*
    @PostMapping("delInfo")
    public String delInfo(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultPalletLogRpc.delInfo(json);
    }
*/
/*
    @PostMapping("saveInfo")
    public String saveInfo(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultPalletLogRpc.saveInfo(json);
    }
*/
    @PostMapping("getFieldParam")
    public String getFieldParam(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultPalletLogRpc.getFieldParam(json);
    }
/*
    @PostMapping("getAllList")
    public String getAllList(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultPalletLogRpc.getAllList(json);
    }
*/
}
