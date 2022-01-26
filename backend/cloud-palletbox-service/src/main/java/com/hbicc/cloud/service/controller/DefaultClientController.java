package com.hbicc.cloud.service.controller;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hbicc.cloud.service.entity.DefaultClient;
import com.hbicc.cloud.service.service.IDefaultClientService;
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
@RequestMapping("/default_client")
public class DefaultClientController {

    @Resource
    private IDefaultClientService defaultClientService;

    @PostMapping("addInfo")
    public ResponseUtil addInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        DefaultClient defaultClient = obj.getBean("info", DefaultClient.class);
        if (!defaultClient.getSuperpwd().equals("")) {
            defaultClient.setSuperpwd(SecureUtil.md5(defaultClient.getSuperpwd()));
        } else {
            defaultClient.setSuperpwd(null);
        }
        return ResponseUtil.out(defaultClientService.add(defaultClient));
    }
    @PostMapping("delInfo")
    public ResponseUtil delInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String id = obj.getStr("id");
        return ResponseUtil.out(defaultClientService.delete(id));
    }

    /**
     * 保存
     * @param json
     * @return
     */
    @PostMapping("saveInfo")
    public ResponseUtil saveInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        DefaultClient defaultClient = obj.getBean("info", DefaultClient.class);
//        String id = obj.getStr("id");
        if (!defaultClient.getSuperpwd().equals("")) {
            defaultClient.setSuperpwd(SecureUtil.md5(defaultClient.getSuperpwd()));
        } else {
            defaultClient.setSuperpwd(null);
        }
        return ResponseUtil.out(defaultClientService.update(defaultClient));
    }



    @PostMapping("getList")
    public ResponseUtil getList(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        JSONObject query = obj.getJSONObject("query");
        JSONArray order = obj.getJSONArray("order");
        Integer page = obj.getInt("page");
        Integer limit = obj.getInt("limit");
        Map<String, Object> result = defaultClientService.page(query, order, page, limit);
        List<DefaultClient> records  = (List<DefaultClient>) result.get("records");
        Map<String, Object> rolesParam = defaultClientService.param("roles", JSONUtil.createObj());
        result.put("roles_param", rolesParam);
        Map<String, Object> statusParam = defaultClientService.param("status", JSONUtil.createObj());
        result.put("status_param", statusParam);
        return ResponseUtil.out(result);
    }
    @PostMapping("getInfo")
    public ResponseUtil getInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String id = obj.getStr("id");
        Map<String, Object> result = new HashMap<>();
        DefaultClient defaultClient = defaultClientService.info(id);
        defaultClient.setSuperpwd("");
        result.put("info", defaultClient);
        Map<String, Object> rolesParam = defaultClientService.param("roles", JSONUtil.createObj());
        result.put("roles_param", rolesParam);
        Map<String, Object> statusParam = defaultClientService.param("status", JSONUtil.createObj());
        result.put("status_param", statusParam);
        return ResponseUtil.out(result);
    }
    @PostMapping("getFieldParam")
    public ResponseUtil param(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String field = obj.getStr("field");
        JSONObject query = obj.getJSONObject("query");
        return ResponseUtil.out(defaultClientService.param(field, query));
    }
    @PostMapping("getAllList")
    public ResponseUtil getAllList(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        JSONObject query = obj.getJSONObject("query");
        JSONArray order = obj.getJSONArray("order");
        Map<String, Object> result = defaultClientService.all(query, order);
        // List<DefaultClient> records  = (List<DefaultClient>) result.get("records");
        return ResponseUtil.out(result);
    }
}
