package com.hbicc.cloud.admin.controller;
import com.hbicc.cloud.admin.rpc.TokenRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    TokenRpc tokenRpc;

    @PostMapping(value = "admin/get", produces = {"application/json;charset=UTF-8"})
    public String adminGet(@RequestBody  String json) {
        return tokenRpc.adminGet(json);
    }


}
