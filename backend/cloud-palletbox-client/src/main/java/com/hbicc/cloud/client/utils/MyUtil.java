package com.hbicc.cloud.client.utils;
import java.util.Map;
import cn.hutool.json.*;


public class MyUtil {

    public static String clientIdWhere(String json, Map<String, Object> userInfo) {
        JSONObject obj = JSONUtil.parseObj(json);
        JSONObject query = obj.getJSONObject("query");
        query.set("eq_client_id", userInfo.get("client_id").toString());
        obj.set("query", query);
        return obj.toString();
    }

    public static String clientIdAdd(String json, Map<String, Object> userInfo) {
        JSONObject obj = JSONUtil.parseObj(json);
        JSONObject info = obj.getJSONObject("info");
        info.set("clientId", userInfo.get("client_id").toString());
        obj.set("info", info);
        return obj.toString();
    }

}
