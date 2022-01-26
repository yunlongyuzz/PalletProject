package com.hbicc.cloud.client.controller;
import java.util.Map;
import com.hbicc.cloud.client.rpc.DefaultClientUserNodeRpc;
import com.hbicc.cloud.client.utils.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/default_client_user_node")
public class DefaultClientUserNodeController {
    @Autowired
    DefaultClientUserNodeRpc defaultClientUserNodeRpc;
    @PostMapping("getList")
    public String getList(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultClientUserNodeRpc.getList(json);
    }
    @PostMapping("getInfo")
    public String getInfo(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultClientUserNodeRpc.getInfo(json);
    }
    @PostMapping("addInfo")
    public String addInfo(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultClientUserNodeRpc.addInfo(json);
    }
    @PostMapping("delInfo")
    public String delInfo(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultClientUserNodeRpc.delInfo(json);
    }
    @PostMapping("saveInfo")
    public String saveInfo(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultClientUserNodeRpc.saveInfo(json);
    }
    @PostMapping("getFieldParam")
    public String getFieldParam(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultClientUserNodeRpc.getFieldParam(json);
    }
/*
    @PostMapping("getAllList")
    public String getAllList(@RequestBody  String json, @RequestAttribute("user_info") Map<String, Object> userInfo) {
        return defaultClientUserNodeRpc.getAllList(json);
    }
*/
}
