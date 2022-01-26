package com.hbicc.cloud.service.controller;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hbicc.cloud.service.entity.DefaultPallet;
import com.hbicc.cloud.service.service.IDefaultPalletService;
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
@RequestMapping("/default_pallet")
public class DefaultPalletController {

    @Resource
    private IDefaultPalletService defaultPalletService;


    @PostMapping("addInfo")
    public ResponseUtil addInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        DefaultPallet defaultPallet = obj.getBean("info", DefaultPallet.class);
        return ResponseUtil.out(defaultPalletService.add(defaultPallet));
    }


    @PostMapping("delInfo")
    public ResponseUtil delInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String id = obj.getStr("id");
        return ResponseUtil.out(defaultPalletService.delete(id));
    }
    @PostMapping("saveInfo")
    public ResponseUtil saveInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        DefaultPallet defaultPallet = obj.getBean("info", DefaultPallet.class);
//        String id = obj.getStr("id");
        return ResponseUtil.out(defaultPalletService.update(defaultPallet));
    }
    @PostMapping("getList")
    public ResponseUtil getList(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        JSONObject query = obj.getJSONObject("query");
        JSONArray order = obj.getJSONArray("order");
        Integer page = obj.getInt("page");
        Integer limit = obj.getInt("limit");
        Map<String, Object> result = defaultPalletService.page(query, order, page, limit);
        List<DefaultPallet> records  = (List<DefaultPallet>) result.get("records");
        // 获取clientIdParam
        List<String> clientIdList = new ArrayList<>();
        records.stream().forEach(u -> {
            if(u.getClientId() != null){
                clientIdList.add(u.getClientId().toString());
            }
        });
        Map<String, Object> clientIdParam = defaultPalletService.param("client_id", JSONUtil.createObj().set("in_id", clientIdList));
        result.put("client_id_param", clientIdParam);
        // 获取clientIdParam
        // 获取curClientIdParam
        List<String> curClientIdList = new ArrayList<>();
        records.stream().forEach(u -> {
            if(u.getCurClientId() != null){
                curClientIdList.add(u.getCurClientId().toString());
            }
        });
        Map<String, Object> curClientIdParam = defaultPalletService.param("cur_client_id", JSONUtil.createObj().set("in_id", curClientIdList));
        result.put("cur_client_id_param", curClientIdParam);
        // 获取curClientIdParam
        Map<String, Object> statusParam = defaultPalletService.param("status", JSONUtil.createObj());
        result.put("status_param", statusParam);
        return ResponseUtil.out(result);
    }
    @PostMapping("getInfo")
    public ResponseUtil getInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String id = obj.getStr("id");
        Map<String, Object> result = new HashMap<>();
        DefaultPallet defaultPallet = defaultPalletService.info(id);
        result.put("info", defaultPallet);
        // 获取clientIdParam
        Map<String, Object> clientIdParam = defaultPalletService.param("client_id", JSONUtil.createObj().set("eq_id", defaultPallet.getClientId()));
        result.put("client_id_param", clientIdParam);
        // 获取clientIdParam
        // 获取curClientIdParam
        Map<String, Object> curClientIdParam = defaultPalletService.param("cur_client_id", JSONUtil.createObj().set("eq_id", defaultPallet.getCurClientId()));
        result.put("cur_client_id_param", curClientIdParam);
        // 获取curClientIdParam
        Map<String, Object> statusParam = defaultPalletService.param("status", JSONUtil.createObj());
        result.put("status_param", statusParam);
        return ResponseUtil.out(result);
    }
    @PostMapping("getFieldParam")
    public ResponseUtil param(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String field = obj.getStr("field");
        JSONObject query = obj.getJSONObject("query");
        return ResponseUtil.out(defaultPalletService.param(field, query));
    }
    @PostMapping("getAllList")
    public ResponseUtil getAllList(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        JSONObject query = obj.getJSONObject("query");
        JSONArray order = obj.getJSONArray("order");
        Map<String, Object> result = defaultPalletService.all(query, order);
        // List<DefaultPallet> records  = (List<DefaultPallet>) result.get("records");
        return ResponseUtil.out(result);
    }
}
