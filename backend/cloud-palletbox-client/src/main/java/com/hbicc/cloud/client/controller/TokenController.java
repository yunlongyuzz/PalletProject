package com.hbicc.cloud.client.controller;
import com.hbicc.cloud.client.rpc.TokenRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    TokenRpc tokenRpc;

    @PostMapping(value = "client/get", produces = {"application/json;charset=UTF-8"})
    public String clientGet(@RequestBody  String json, @RequestHeader("Host") String host) {
        JSONObject obj = JSONUtil.parseObj(json);
        obj.set("en_name", host.substring(0, host.indexOf(".")));
        json = obj.toString();
        return tokenRpc.clientGet(json);
    }
}
