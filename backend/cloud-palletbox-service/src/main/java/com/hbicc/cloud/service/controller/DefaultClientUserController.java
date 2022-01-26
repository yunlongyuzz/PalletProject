package com.hbicc.cloud.service.controller;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hbicc.cloud.service.entity.DefaultClientUser;
import com.hbicc.cloud.service.service.IDefaultClientUserService;
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
@RequestMapping("/default_client_user")
public class DefaultClientUserController {

    @Resource
    private IDefaultClientUserService defaultClientUserService;


    @PostMapping("addInfo")
    public ResponseUtil addInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        DefaultClientUser defaultClientUser = obj.getBean("info", DefaultClientUser.class);
        if (!defaultClientUser.getPassword().equals("")) {
            defaultClientUser.setPassword(SecureUtil.md5(defaultClientUser.getPassword()));
        } else {
            defaultClientUser.setPassword(null);
        }
        return ResponseUtil.out(defaultClientUserService.add(defaultClientUser));
    }


    @PostMapping("delInfo")
    public ResponseUtil delInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String id = obj.getStr("id");
        return ResponseUtil.out(defaultClientUserService.delete(id));
    }

    /**
     * 保存clientUser
     * @param json
     * @return
     */
    @PostMapping("saveInfo")
    public ResponseUtil saveInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        DefaultClientUser defaultClientUser = obj.getBean("info", DefaultClientUser.class);
//        String id = obj.getStr("id");
        if (!defaultClientUser.getPassword().equals("")) {
            defaultClientUser.setPassword(SecureUtil.md5(defaultClientUser.getPassword()));
        } else {
            defaultClientUser.setPassword(null);
        }
        return ResponseUtil.out(defaultClientUserService.update(defaultClientUser));
    }



    @PostMapping("getList")
    public ResponseUtil getList(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        JSONObject query = obj.getJSONObject("query");
        JSONArray order = obj.getJSONArray("order");
        Integer page = obj.getInt("page");
        Integer limit = obj.getInt("limit");
        Map<String, Object> result = defaultClientUserService.page(query, order, page, limit);
        List<DefaultClientUser> records  = (List<DefaultClientUser>) result.get("records");
        // 获取clientIdParam
        List<String> clientIdList = new ArrayList<>();
        records.stream().forEach(u -> {
            if(u.getClientId() != null){
                clientIdList.add(u.getClientId().toString());
            }
        });
        Map<String, Object> clientIdParam = defaultClientUserService.param("client_id", JSONUtil.createObj().set("in_id", clientIdList));
        result.put("client_id_param", clientIdParam);
        // 获取clientIdParam
        Map<String, Object> statusParam = defaultClientUserService.param("status", JSONUtil.createObj());
        result.put("status_param", statusParam);
        return ResponseUtil.out(result);
    }


    @PostMapping("getInfo")
    public ResponseUtil getInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String id = obj.getStr("id");
        Map<String, Object> result = new HashMap<>();
        DefaultClientUser defaultClientUser = defaultClientUserService.info(id);
        defaultClientUser.setPassword("");
        result.put("info", defaultClientUser);
        // 获取clientIdParam
        Map<String, Object> clientIdParam = defaultClientUserService.param("client_id", JSONUtil.createObj().set("eq_id", defaultClientUser.getClientId()));
        result.put("client_id_param", clientIdParam);
        // 获取clientIdParam
        Map<String, Object> statusParam = defaultClientUserService.param("status", JSONUtil.createObj());
        result.put("status_param", statusParam);
        return ResponseUtil.out(result);
    }


    @PostMapping("getFieldParam")
    public ResponseUtil param(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String field = obj.getStr("field");
        JSONObject query = obj.getJSONObject("query");
        return ResponseUtil.out(defaultClientUserService.param(field, query));
    }


    @PostMapping("getAllList")
    public ResponseUtil getAllList(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        JSONObject query = obj.getJSONObject("query");
        JSONArray order = obj.getJSONArray("order");
        Map<String, Object> result = defaultClientUserService.all(query, order);
        // List<DefaultClientUser> records  = (List<DefaultClientUser>) result.get("records");
        return ResponseUtil.out(result);
    }


}
