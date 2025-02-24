package com.sennan.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "xunfei.client")
public class XunFeiProperties {
    /**
     * 讯飞大模型秘钥
     */
    private static String appid;
    private static String apikey;

    public static String getAppid() {
        return appid;
    }

    public static String getApikey() {
        return apikey;
    }
    public static void setApikey(String apikey) {
        XunFeiProperties.apikey = apikey;
    }

    public static void setAppid(String appid) {
        XunFeiProperties.appid = appid;
    }
}
