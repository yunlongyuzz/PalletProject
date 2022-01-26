package com.hbicc.cloud.service.controller;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hbicc.cloud.service.entity.DefaultClientUserNode;
import com.hbicc.cloud.service.service.IDefaultClientUserNodeService;
import com.hbicc.cloud.common.utils.ResponseUtil;
// import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// @Slf4j
@RestController
@RequestMapping("/default_client_user_node")
public class DefaultClientUserNodeController {
    @Resource
    private IDefaultClientUserNodeService defaultClientUserNodeService;
    @PostMapping("addInfo")
    public ResponseUtil addInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        DefaultClientUserNode defaultClientUserNode = obj.getBean("info", DefaultClientUserNode.class);
        return ResponseUtil.out(defaultClientUserNodeService.add(defaultClientUserNode));
    }
    @PostMapping("delInfo")
    public ResponseUtil delInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String id = obj.getStr("id");
        return ResponseUtil.out(defaultClientUserNodeService.delete(id));
    }
    @PostMapping("saveInfo")
    public ResponseUtil saveInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        DefaultClientUserNode defaultClientUserNode = obj.getBean("info", DefaultClientUserNode.class);
//        String id = obj.getStr("id");
        return ResponseUtil.out(defaultClientUserNodeService.update(defaultClientUserNode));
    }
    @PostMapping("getList")
    public ResponseUtil getList(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        JSONObject query = obj.getJSONObject("query");
        JSONArray order = obj.getJSONArray("order");
        Integer page = obj.getInt("page");
        Integer limit = obj.getInt("limit");
        Map<String, Object> result = defaultClientUserNodeService.page(query, order, page, limit);
        List<DefaultClientUserNode> records  = (List<DefaultClientUserNode>) result.get("records");
        return ResponseUtil.out(result);
    }
    @PostMapping("getInfo")
    public ResponseUtil getInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String id = obj.getStr("id");
        Map<String, Object> result = new HashMap<>();
        DefaultClientUserNode defaultClientUserNode = defaultClientUserNodeService.info(id);
        result.put("info", defaultClientUserNode);
        return ResponseUtil.out(result);
    }
    @PostMapping("getFieldParam")
    public ResponseUtil param(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String field = obj.getStr("field");
        JSONObject query = obj.getJSONObject("query");
        return ResponseUtil.out(defaultClientUserNodeService.param(field, query));
    }
    @PostMapping("getAllList")
    public ResponseUtil getAllList(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        JSONObject query = obj.getJSONObject("query");
        JSONArray order = obj.getJSONArray("order");
        Map<String, Object> result = defaultClientUserNodeService.all(query, order);
        // List<DefaultClientUserNode> records  = (List<DefaultClientUserNode>) result.get("records");
        return ResponseUtil.out(result);
    }
}
