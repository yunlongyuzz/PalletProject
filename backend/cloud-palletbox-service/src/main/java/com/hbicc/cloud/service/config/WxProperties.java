package com.hbicc.cloud.service.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付功能
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wechat")
public class WxProperties {
    private String appId;
    private String appSecret;
    private String mchId;
    private String mchSerialNumber;
    private String mchkeyPath;
    private String apiV3Key;
    private String serviceId;
    private String notifySignUrl;
    private String notifyJsPayUrl;
    private String notifyRefundUrl;
    private String notifyCompleteOrderUrl;
    private String notifyCreateOrderUrl;
    private String notifyCancelOrderUrl;
}
