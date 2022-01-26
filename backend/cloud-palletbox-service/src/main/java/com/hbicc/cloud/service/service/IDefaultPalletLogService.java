package com.hbicc.cloud.service.service;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
//import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hbicc.cloud.service.entity.DefaultPalletLog;
import java.util.Map;
public interface IDefaultPalletLogService extends IService<DefaultPalletLog> {
    /**
     * 查询托盘流转日志分页数据
     *
     * @param  where
     * @param  orderBy
     * @param  page    页码
     * @param  limit   每页条数
     * @return
     */
    Map<String, Object> page(JSONObject where, JSONArray orderBy, Integer page, Integer limit);
    /**
     * 查询托盘流转日志所有数据
     *
     * @param  where
     * @param  orderBy
     * @return
     */
    Map<String, Object> all(JSONObject where, JSONArray orderBy);
    /**
     * 添加托盘流转日志数据
     *
     * @param  defaultPalletLog 托盘流转日志.
     * @return  int
     */
    int add(DefaultPalletLog defaultPalletLog);
    /**
     * 删除托盘流转日志数据
     *
     * @param  id 主键
     * @return  int
     */
    int delete(String id);
    /**
     * 修改托盘流转日志数据
     *
     * @param  defaultPalletLog 托盘流转日志.
     * @return  int
     */
    int update(DefaultPalletLog defaultPalletLog);
    /**
     * id查询数据
     *
     * @param  id id
     * @return  defaultPalletLog.
     */
    DefaultPalletLog info(String id);
    /**
     * 获取字段相关属性
     *
     * @param  field
     * @param  where
     * @return
     */
    Map<String, Object> param(String field, JSONObject where);
}
