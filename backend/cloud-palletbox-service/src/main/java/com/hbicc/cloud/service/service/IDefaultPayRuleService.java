package com.hbicc.cloud.service.service;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
//import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hbicc.cloud.service.entity.DefaultPayRule;
import java.util.Map;
public interface IDefaultPayRuleService extends IService<DefaultPayRule> {
    /**
     * 查询支付规则分页数据
     *
     * @param  where
     * @param  orderBy
     * @param  page    页码
     * @param  limit   每页条数
     * @return
     */
    Map<String, Object> page(JSONObject where, JSONArray orderBy, Integer page, Integer limit);
    /**
     * 查询支付规则所有数据
     *
     * @param  where
     * @param  orderBy
     * @return
     */
    Map<String, Object> all(JSONObject where, JSONArray orderBy);
    /**
     * 添加支付规则数据
     *
     * @param  defaultPayRule 支付规则.
     * @return  int
     */
    int add(DefaultPayRule defaultPayRule);
    /**
     * 删除支付规则数据
     *
     * @param  id 主键
     * @return  int
     */
    int delete(String id);
    /**
     * 修改支付规则数据
     *
     * @param  defaultPayRule 支付规则.
     * @return  int
     */
    int update(DefaultPayRule defaultPayRule);
    /**
     * id查询数据
     *
     * @param  id id
     * @return  defaultPayRule.
     */
    DefaultPayRule info(String id);
    /**
     * 获取字段相关属性
     *
     * @param  field
     * @param  where
     * @return
     */
    Map<String, Object> param(String field, JSONObject where);
}
