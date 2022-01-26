package com.hbicc.cloud.service.controller;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hbicc.cloud.service.entity.DefaultPayRule;
import com.hbicc.cloud.service.service.IDefaultPayRuleService;
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
@RequestMapping("/default_pay_rule")
public class DefaultPayRuleController {

    @Resource
    private IDefaultPayRuleService defaultPayRuleService;


    @PostMapping("addInfo")
    public ResponseUtil addInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        DefaultPayRule defaultPayRule = obj.getBean("info", DefaultPayRule.class);
        return ResponseUtil.out(defaultPayRuleService.add(defaultPayRule));
    }

    @PostMapping("delInfo")
    public ResponseUtil delInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String id = obj.getStr("id");
        return ResponseUtil.out(defaultPayRuleService.delete(id));
    }


    @PostMapping("saveInfo")
    public ResponseUtil saveInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        DefaultPayRule defaultPayRule = obj.getBean("info", DefaultPayRule.class);
//        String id = obj.getStr("id");
        return ResponseUtil.out(defaultPayRuleService.update(defaultPayRule));
    }


    @PostMapping("getList")
    public ResponseUtil getList(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        JSONObject query = obj.getJSONObject("query");
        JSONArray order = obj.getJSONArray("order");
        Integer page = obj.getInt("page");
        Integer limit = obj.getInt("limit");
        Map<String, Object> result = defaultPayRuleService.page(query, order, page, limit);
        List<DefaultPayRule> records  = (List<DefaultPayRule>) result.get("records");
        // 获取palletClientIdParam
        List<String> palletClientIdList = new ArrayList<>();
        records.stream().forEach(u -> {
            if(u.getPalletClientId() != null){
                palletClientIdList.add(u.getPalletClientId().toString());
            }
        });
        Map<String, Object> palletClientIdParam = defaultPayRuleService.param("pallet_client_id", JSONUtil.createObj().set("in_id", palletClientIdList));
        result.put("pallet_client_id_param", palletClientIdParam);
        // 获取palletClientIdParam
        Map<String, Object> feeTypeParam = defaultPayRuleService.param("fee_type", JSONUtil.createObj());
        result.put("fee_type_param", feeTypeParam);
        // 获取payClientIdParam
        List<String> payClientIdList = new ArrayList<>();
        records.stream().forEach(u -> {
            if(u.getPayClientId() != null){
                payClientIdList.add(u.getPayClientId().toString());
            }
        });
        Map<String, Object> payClientIdParam = defaultPayRuleService.param("pay_client_id", JSONUtil.createObj().set("in_id", payClientIdList));
        result.put("pay_client_id_param", payClientIdParam);
        // 获取payClientIdParam
        // 获取fromClientIdParam
        List<String> fromClientIdList = new ArrayList<>();
        records.stream().forEach(u -> {
            if(u.getFromClientId() != null){
                fromClientIdList.add(u.getFromClientId().toString());
            }
        });
        Map<String, Object> fromClientIdParam = defaultPayRuleService.param("from_client_id", JSONUtil.createObj().set("in_id", fromClientIdList));
        result.put("from_client_id_param", fromClientIdParam);
        // 获取fromClientIdParam
        // 获取toClientIdParam
        List<String> toClientIdList = new ArrayList<>();
        records.stream().forEach(u -> {
            if(u.getToClientId() != null){
                toClientIdList.add(u.getToClientId().toString());
            }
        });
        Map<String, Object> toClientIdParam = defaultPayRuleService.param("to_client_id", JSONUtil.createObj().set("in_id", toClientIdList));
        result.put("to_client_id_param", toClientIdParam);
        // 获取toClientIdParam
        return ResponseUtil.out(result);
    }


    @PostMapping("getInfo")
    public ResponseUtil getInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String id = obj.getStr("id");
        Map<String, Object> result = new HashMap<>();
        DefaultPayRule defaultPayRule = defaultPayRuleService.info(id);
        result.put("info", defaultPayRule);
        // 获取palletClientIdParam
        Map<String, Object> palletClientIdParam = defaultPayRuleService.param("pallet_client_id", JSONUtil.createObj().set("eq_id", defaultPayRule.getPalletClientId()));
        result.put("pallet_client_id_param", palletClientIdParam);
        // 获取palletClientIdParam
        Map<String, Object> feeTypeParam = defaultPayRuleService.param("fee_type", JSONUtil.createObj());
        result.put("fee_type_param", feeTypeParam);
        // 获取payClientIdParam
        Map<String, Object> payClientIdParam = defaultPayRuleService.param("pay_client_id", JSONUtil.createObj().set("eq_id", defaultPayRule.getPayClientId()));
        result.put("pay_client_id_param", payClientIdParam);
        // 获取payClientIdParam
        // 获取fromClientIdParam
        Map<String, Object> fromClientIdParam = defaultPayRuleService.param("from_client_id", JSONUtil.createObj().set("eq_id", defaultPayRule.getFromClientId()));
        result.put("from_client_id_param", fromClientIdParam);
        // 获取fromClientIdParam
        // 获取toClientIdParam
        Map<String, Object> toClientIdParam = defaultPayRuleService.param("to_client_id", JSONUtil.createObj().set("eq_id", defaultPayRule.getToClientId()));
        result.put("to_client_id_param", toClientIdParam);
        // 获取toClientIdParam
        return ResponseUtil.out(result);
    }


    @PostMapping("getFieldParam")
    public ResponseUtil param(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String field = obj.getStr("field");
        JSONObject query = obj.getJSONObject("query");
        return ResponseUtil.out(defaultPayRuleService.param(field, query));
    }


    @PostMapping("getAllList")
    public ResponseUtil getAllList(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        JSONObject query = obj.getJSONObject("query");
        JSONArray order = obj.getJSONArray("order");
        Map<String, Object> result = defaultPayRuleService.all(query, order);
        // List<DefaultPayRule> records  = (List<DefaultPayRule>) result.get("records");
        return ResponseUtil.out(result);
    }

}
