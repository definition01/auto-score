package com.sennan.common.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "auto.jwt")
@Component
public class JwtProperties {

    /**
     * 管理端员工生成jwt令牌相关配置
     */
    private String secretKey;
    private long ttl;
    private String tokenName;


    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        secretKey = secretKey;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        ttl = ttl;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        tokenName = tokenName;
    }



}
