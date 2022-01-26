package com.hbicc.cloud.upload.controller;
import com.hbicc.cloud.upload.rpc.TokenRpc;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    TokenRpc tokenRpc;

    // @PostMapping("upload/get")
    // public String uploadGet(@RequestBody  String json) {
    //   return tokenRpc.uploadGet(json);
    // }

}
