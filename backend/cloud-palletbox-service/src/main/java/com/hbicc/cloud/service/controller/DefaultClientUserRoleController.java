package com.hbicc.cloud.service.controller;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hbicc.cloud.service.entity.DefaultClientUserRole;
import com.hbicc.cloud.service.service.IDefaultClientUserRoleService;
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
@RequestMapping("/default_client_user_role")
public class DefaultClientUserRoleController {
    @Resource
    private IDefaultClientUserRoleService defaultClientUserRoleService;
    @PostMapping("addInfo")
    public ResponseUtil addInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        DefaultClientUserRole defaultClientUserRole = obj.getBean("info", DefaultClientUserRole.class);
        return ResponseUtil.out(defaultClientUserRoleService.add(defaultClientUserRole));
    }
    @PostMapping("delInfo")
    public ResponseUtil delInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String id = obj.getStr("id");
        return ResponseUtil.out(defaultClientUserRoleService.delete(id));
    }
    @PostMapping("saveInfo")
    public ResponseUtil saveInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        DefaultClientUserRole defaultClientUserRole = obj.getBean("info", DefaultClientUserRole.class);
//        String id = obj.getStr("id");
        return ResponseUtil.out(defaultClientUserRoleService.update(defaultClientUserRole));
    }
    @PostMapping("getList")
    public ResponseUtil getList(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        JSONObject query = obj.getJSONObject("query");
        JSONArray order = obj.getJSONArray("order");
        Integer page = obj.getInt("page");
        Integer limit = obj.getInt("limit");
        Map<String, Object> result = defaultClientUserRoleService.page(query, order, page, limit);
        List<DefaultClientUserRole> records  = (List<DefaultClientUserRole>) result.get("records");
        return ResponseUtil.out(result);
    }
    @PostMapping("getInfo")
    public ResponseUtil getInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String id = obj.getStr("id");
        Map<String, Object> result = new HashMap<>();
        DefaultClientUserRole defaultClientUserRole = defaultClientUserRoleService.info(id);
        result.put("info", defaultClientUserRole);
        return ResponseUtil.out(result);
    }
    @PostMapping("getFieldParam")
    public ResponseUtil param(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String field = obj.getStr("field");
        JSONObject query = obj.getJSONObject("query");
        return ResponseUtil.out(defaultClientUserRoleService.param(field, query));
    }
    @PostMapping("getAllList")
    public ResponseUtil getAllList(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        JSONObject query = obj.getJSONObject("query");
        JSONArray order = obj.getJSONArray("order");
        Map<String, Object> result = defaultClientUserRoleService.all(query, order);
        // List<DefaultClientUserRole> records  = (List<DefaultClientUserRole>) result.get("records");
        return ResponseUtil.out(result);
    }
}
