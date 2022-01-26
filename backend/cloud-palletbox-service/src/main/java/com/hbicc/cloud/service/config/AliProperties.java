package com.hbicc.cloud.service.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里支付
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "alipay")
public class AliProperties {
    private String appId;
    private String yourPrivateKey;
    private String alipayPublicKey;
    private String aesKey;
    private String gateway;
    private String notifyJsPayUrl;
    private String notifyPayUrl;
    private String notifyGatewayUrl;
    private String notifySignAgreementUrl;
    private String notifyUnsignAgreementUrl;
    private String notifyCancelOrderUrl;
    private String notifyRefundUrl;
}
