package com.hbicc.cloud.service;
import com.github.rexsheng.mybatis.annotation.EnableMybatisExtension;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@EnableTransactionManagement
@MapperScan({"com.hbicc.cloud.service.mapper"})
@ServletComponentScan
@EnableMybatisExtension
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
