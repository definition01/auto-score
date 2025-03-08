package com.sennan.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "xunfei.client")
public class XunFeiProperties {
    /**
     * 讯飞大模型秘钥
     */
    private String appid;
    private String apikey;

    public String getAppid() {
        return appid;
    }

    public String getApikey() {
        return apikey;
    }
    public void setApikey(String apikey) {
       this.apikey = apikey;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}
