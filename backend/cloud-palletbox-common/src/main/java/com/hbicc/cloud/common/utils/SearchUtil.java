package com.hbicc.cloud.common.utils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

/**
 * 搜索功能
 */
public class SearchUtil {
    public static <T> QueryWrapper<T> parseWhereSql(JSONObject obj) {

        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();

        if (obj != null) {
        obj.forEach((k, v) -> {
            String op = StrUtil.sub(k, 0, 2);
            String key = StrUtil.sub(k, 3, k.length());
            if((!"".equals(v)) && (null != v)){
            switch (op) {
                case "eq":
                queryWrapper.eq("`" + key + "`", v);
                break;
                case "lk":
                queryWrapper.like("`" + key + "`", v);
                break;
                case "gt":
                queryWrapper.gt("`" + key + "`", v);
                break;
                case "ge":
                queryWrapper.ge("`" + key + "`", v);
                break;
                case "lt":
                queryWrapper.lt("`" + key + "`", v);
                break;
                case "le":
                queryWrapper.le("`" + key + "`", v);
                break;
                case "in":
                if (((JSONArray) v).size() > 0) {
                    queryWrapper.in("`" + key + "`", ((JSONArray) v).toArray());
                }
                break;
                case "ni":
                if (((JSONArray) v).size() > 0) {
                    queryWrapper.notIn("`" + key + "`", ((JSONArray) v).toArray());
                }
                break;
                case "bw":
                JSONArray arr = obj.getJSONArray(k);
                queryWrapper.between("`" + key + "`", arr.get(0), arr.get(1));
                break;
            }
            }
        });
        }
        return queryWrapper;
    }
}
