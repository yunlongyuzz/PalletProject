package com.hbicc.cloud.service.service;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
//import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hbicc.cloud.service.entity.DefaultClient;
import java.util.Map;
public interface IDefaultClientService extends IService<DefaultClient> {
    /**
     * 查询客户分页数据
     *
     * @param  where
     * @param  orderBy
     * @param  page    页码
     * @param  limit   每页条数
     * @return
     */
    Map<String, Object> page(JSONObject where, JSONArray orderBy, Integer page, Integer limit);
    /**
     * 查询客户所有数据
     *
     * @param  where
     * @param  orderBy
     * @return
     */
    Map<String, Object> all(JSONObject where, JSONArray orderBy);
    /**
     * 添加客户数据
     *
     * @param  defaultClient 客户.
     * @return  int
     */
    int add(DefaultClient defaultClient);
    /**
     * 删除客户数据
     *
     * @param  id 主键
     * @return  int
     */
    int delete(String id);
    /**
     * 修改客户数据
     *
     * @param  defaultClient 客户.
     * @return  int
     */
    int update(DefaultClient defaultClient);
    /**
     * id查询数据
     *
     * @param  id id
     * @return  defaultClient.
     */
    DefaultClient info(String id);
    /**
     * 获取字段相关属性
     *
     * @param  field
     * @param  where
     * @return
     */
    Map<String, Object> param(String field, JSONObject where);
}
