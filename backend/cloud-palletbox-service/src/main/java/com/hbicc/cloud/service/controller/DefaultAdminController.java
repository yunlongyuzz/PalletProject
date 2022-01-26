package com.hbicc.cloud.service.controller;
import cn.hutool.core.lang.Console;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hbicc.cloud.service.entity.DefaultAdmin;
import com.hbicc.cloud.service.service.IDefaultAdminService;
import com.hbicc.cloud.common.utils.ResponseUtil;
// import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// @Slf4j
@RestController
@RequestMapping("/default_admin")
public class DefaultAdminController {


    @Resource
    private IDefaultAdminService defaultAdminService;

    /**
     * 添加admin信息
     * @param json
     * @return
     */
    @PostMapping("addInfo")
    public ResponseUtil addInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        DefaultAdmin defaultAdmin = obj.getBean("info", DefaultAdmin.class);
        if (!defaultAdmin.getPassword().equals("")) {

            //启动盐值加密
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String ps = defaultAdmin.getPassword();

            String encode = passwordEncoder.encode(ps);
            defaultAdmin.setPassword(encode);

        } else {
            defaultAdmin.setPassword(null);
        }
        return ResponseUtil.out(defaultAdminService.add(defaultAdmin));
    }


    /**
     * 更新admin信息
     * @param json
     * @return
     */
    @PostMapping("saveInfo")
    public ResponseUtil saveInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        DefaultAdmin defaultAdmin = obj.getBean("info", DefaultAdmin.class);
//        String id = obj.getStr("id");
        if (!defaultAdmin.getPassword().equals("")) {

            //启动盐值加密
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String ps = defaultAdmin.getPassword();

            String encode = passwordEncoder.encode(ps);
            defaultAdmin.setPassword(encode);

        } else {
            defaultAdmin.setPassword(null);
        }
        return ResponseUtil.out(defaultAdminService.update(defaultAdmin));
    }


    @PostMapping("getInfo")
    public ResponseUtil getInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String id = obj.getStr("id");
        Map<String, Object> result = new HashMap<>();
        DefaultAdmin defaultAdmin = defaultAdminService.info(id);
        defaultAdmin.setPassword("");
        result.put("info", defaultAdmin);
        Map<String, Object> isValidParam = defaultAdminService.param("is_valid", JSONUtil.createObj());
        result.put("is_valid_param", isValidParam);
        return ResponseUtil.out(result);
    }


    @PostMapping("delInfo")
    public ResponseUtil delInfo(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String id = obj.getStr("id");
        return ResponseUtil.out(defaultAdminService.delete(id));
    }


    @PostMapping("getList")
    public ResponseUtil getList(@RequestBody  String json) {
        Console.log("json: {}", json);
        JSONObject obj = JSONUtil.parseObj(json);
        JSONObject query = obj.getJSONObject("query");
        JSONArray order = obj.getJSONArray("order");
        Integer page = obj.getInt("page");
        Integer limit = obj.getInt("limit");
        Map<String, Object> result = defaultAdminService.page(query, order, page, limit);
        List<DefaultAdmin> records  = (List<DefaultAdmin>) result.get("records");
        Map<String, Object> isValidParam = defaultAdminService.param("is_valid", JSONUtil.createObj());
        result.put("is_valid_param", isValidParam);
        return ResponseUtil.out(result);
    }



    @PostMapping("getFieldParam")
    public ResponseUtil param(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String field = obj.getStr("field");
        JSONObject query = obj.getJSONObject("query");
        return ResponseUtil.out(defaultAdminService.param(field, query));
    }


    @PostMapping("getAllList")
    public ResponseUtil getAllList(@RequestBody  String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        JSONObject query = obj.getJSONObject("query");
        JSONArray order = obj.getJSONArray("order");
        Map<String, Object> result = defaultAdminService.all(query, order);
        // List<DefaultAdmin> records  = (List<DefaultAdmin>) result.get("records");
        return ResponseUtil.out(result);
    }


}
