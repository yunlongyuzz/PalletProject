package com.hbicc.cloud.service.controller;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hbicc.cloud.service.entity.DefaultOrder;
import com.hbicc.cloud.service.service.IDefaultOrderService;
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
@RequestMapping("/default_order")
public class DefaultOrderController {
    @Resource
    private IDefaultOrderService defaultOrderService;
    @PostMapping("addInfo")
    public ResponseUtil addInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        DefaultOrder defaultOrder = obj.getBean("info", DefaultOrder.class);
        return ResponseUtil.out(defaultOrderService.add(defaultOrder));
    }
    @PostMapping("delInfo")
    public ResponseUtil delInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String id = obj.getStr("id");
        return ResponseUtil.out(defaultOrderService.delete(id));
    }
    @PostMapping("saveInfo")
    public ResponseUtil saveInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        DefaultOrder defaultOrder = obj.getBean("info", DefaultOrder.class);
//        String id = obj.getStr("id");
        return ResponseUtil.out(defaultOrderService.update(defaultOrder));
    }




    @PostMapping("getList")
    public ResponseUtil getList(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        JSONObject query = obj.getJSONObject("query");
        JSONArray order = obj.getJSONArray("order");
        Integer page = obj.getInt("page");
        Integer limit = obj.getInt("limit");
        Map<String, Object> result = defaultOrderService.page(query, order, page, limit);
        List<DefaultOrder> records  = (List<DefaultOrder>) result.get("records");
        // 获取collectClientIdParam
        List<String> collectClientIdList = new ArrayList<>();
        records.stream().forEach(u -> {
            if(u.getCollectClientId() != null){
                collectClientIdList.add(u.getCollectClientId().toString());
            }
        });
        Map<String, Object> collectClientIdParam = defaultOrderService.param("collect_client_id", JSONUtil.createObj().set("in_id", collectClientIdList));
        result.put("collect_client_id_param", collectClientIdParam);
        // 获取collectClientIdParam
        Map<String, Object> feeTypeParam = defaultOrderService.param("fee_type", JSONUtil.createObj());
        result.put("fee_type_param", feeTypeParam);
        // 获取payClientIdParam
        List<String> payClientIdList = new ArrayList<>();
        records.stream().forEach(u -> {
            if(u.getPayClientId() != null){
                payClientIdList.add(u.getPayClientId().toString());
            }
        });
        Map<String, Object> payClientIdParam = defaultOrderService.param("pay_client_id", JSONUtil.createObj().set("in_id", payClientIdList));
        result.put("pay_client_id_param", payClientIdParam);
        // 获取payClientIdParam
        return ResponseUtil.out(result);
    }






    @PostMapping("getInfo")
    public ResponseUtil getInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String id = obj.getStr("id");
        Map<String, Object> result = new HashMap<>();
        DefaultOrder defaultOrder = defaultOrderService.info(id);
        result.put("info", defaultOrder);
        // 获取collectClientIdParam
        Map<String, Object> collectClientIdParam = defaultOrderService.param("collect_client_id", JSONUtil.createObj().set("eq_id", defaultOrder.getCollectClientId()));
        result.put("collect_client_id_param", collectClientIdParam);
        // 获取collectClientIdParam
        Map<String, Object> feeTypeParam = defaultOrderService.param("fee_type", JSONUtil.createObj());
        result.put("fee_type_param", feeTypeParam);
        // 获取payClientIdParam
        Map<String, Object> payClientIdParam = defaultOrderService.param("pay_client_id", JSONUtil.createObj().set("eq_id", defaultOrder.getPayClientId()));
        result.put("pay_client_id_param", payClientIdParam);
        // 获取payClientIdParam
        return ResponseUtil.out(result);
    }
    @PostMapping("getFieldParam")
    public ResponseUtil param(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String field = obj.getStr("field");
        JSONObject query = obj.getJSONObject("query");
        return ResponseUtil.out(defaultOrderService.param(field, query));
    }
    @PostMapping("getAllList")
    public ResponseUtil getAllList(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        JSONObject query = obj.getJSONObject("query");
        JSONArray order = obj.getJSONArray("order");
        Map<String, Object> result = defaultOrderService.all(query, order);
        // List<DefaultOrder> records  = (List<DefaultOrder>) result.get("records");
        return ResponseUtil.out(result);
    }
}
