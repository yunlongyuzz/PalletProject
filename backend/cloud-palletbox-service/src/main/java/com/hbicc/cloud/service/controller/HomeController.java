package com.hbicc.cloud.service.controller;
import javax.annotation.Resource;
import com.hbicc.cloud.common.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "hello!!!";
    }

}
