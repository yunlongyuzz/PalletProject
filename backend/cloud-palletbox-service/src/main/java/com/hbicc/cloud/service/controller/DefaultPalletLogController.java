package com.hbicc.cloud.service.controller;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hbicc.cloud.service.entity.DefaultPalletLog;
import com.hbicc.cloud.service.service.IDefaultPalletLogService;
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
@RequestMapping("/default_pallet_log")
public class DefaultPalletLogController {
    @Resource
    private IDefaultPalletLogService defaultPalletLogService;
    @PostMapping("addInfo")
    public ResponseUtil addInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        DefaultPalletLog defaultPalletLog = obj.getBean("info", DefaultPalletLog.class);
        return ResponseUtil.out(defaultPalletLogService.add(defaultPalletLog));
    }
    @PostMapping("delInfo")
    public ResponseUtil delInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String id = obj.getStr("id");
        return ResponseUtil.out(defaultPalletLogService.delete(id));
    }
    @PostMapping("saveInfo")
    public ResponseUtil saveInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        DefaultPalletLog defaultPalletLog = obj.getBean("info", DefaultPalletLog.class);
//        String id = obj.getStr("id");
        return ResponseUtil.out(defaultPalletLogService.update(defaultPalletLog));
    }
    @PostMapping("getList")
    public ResponseUtil getList(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        JSONObject query = obj.getJSONObject("query");
        JSONArray order = obj.getJSONArray("order");
        Integer page = obj.getInt("page");
        Integer limit = obj.getInt("limit");
        Map<String, Object> result = defaultPalletLogService.page(query, order, page, limit);
        List<DefaultPalletLog> records  = (List<DefaultPalletLog>) result.get("records");
        // 获取fromClientIdParam
        List<String> fromClientIdList = new ArrayList<>();
        records.stream().forEach(u -> {
            if(u.getFromClientId() != null){
                fromClientIdList.add(u.getFromClientId().toString());
            }
        });
        Map<String, Object> fromClientIdParam = defaultPalletLogService.param("from_client_id", JSONUtil.createObj().set("in_id", fromClientIdList));
        result.put("from_client_id_param", fromClientIdParam);
        // 获取fromClientIdParam
        // 获取toClientIdParam
        List<String> toClientIdList = new ArrayList<>();
        records.stream().forEach(u -> {
            if(u.getToClientId() != null){
                toClientIdList.add(u.getToClientId().toString());
            }
        });
        Map<String, Object> toClientIdParam = defaultPalletLogService.param("to_client_id", JSONUtil.createObj().set("in_id", toClientIdList));
        result.put("to_client_id_param", toClientIdParam);
        // 获取toClientIdParam
        Map<String, Object> transTypeParam = defaultPalletLogService.param("trans_type", JSONUtil.createObj());
        result.put("trans_type_param", transTypeParam);
        // 获取userIdParam
        List<String> userIdList = new ArrayList<>();
        records.stream().forEach(u -> {
            if(u.getUserId() != null){
                userIdList.add(u.getUserId().toString());
            }
        });
        Map<String, Object> userIdParam = defaultPalletLogService.param("user_id", JSONUtil.createObj().set("in_id", userIdList));
        result.put("user_id_param", userIdParam);
        // 获取userIdParam
        return ResponseUtil.out(result);
    }
    @PostMapping("getInfo")
    public ResponseUtil getInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String id = obj.getStr("id");
        Map<String, Object> result = new HashMap<>();
        DefaultPalletLog defaultPalletLog = defaultPalletLogService.info(id);
        result.put("info", defaultPalletLog);
        // 获取fromClientIdParam
        Map<String, Object> fromClientIdParam = defaultPalletLogService.param("from_client_id", JSONUtil.createObj().set("eq_id", defaultPalletLog.getFromClientId()));
        result.put("from_client_id_param", fromClientIdParam);
        // 获取fromClientIdParam
        // 获取toClientIdParam
        Map<String, Object> toClientIdParam = defaultPalletLogService.param("to_client_id", JSONUtil.createObj().set("eq_id", defaultPalletLog.getToClientId()));
        result.put("to_client_id_param", toClientIdParam);
        // 获取toClientIdParam
        Map<String, Object> transTypeParam = defaultPalletLogService.param("trans_type", JSONUtil.createObj());
        result.put("trans_type_param", transTypeParam);
        // 获取userIdParam
        Map<String, Object> userIdParam = defaultPalletLogService.param("user_id", JSONUtil.createObj().set("eq_id", defaultPalletLog.getUserId()));
        result.put("user_id_param", userIdParam);
        // 获取userIdParam
        return ResponseUtil.out(result);
    }
    @PostMapping("getFieldParam")
    public ResponseUtil param(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String field = obj.getStr("field");
        JSONObject query = obj.getJSONObject("query");
        return ResponseUtil.out(defaultPalletLogService.param(field, query));
    }
    @PostMapping("getAllList")
    public ResponseUtil getAllList(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        JSONObject query = obj.getJSONObject("query");
        JSONArray order = obj.getJSONArray("order");
        Map<String, Object> result = defaultPalletLogService.all(query, order);
        // List<DefaultPalletLog> records  = (List<DefaultPalletLog>) result.get("records");
        return ResponseUtil.out(result);
    }
}
