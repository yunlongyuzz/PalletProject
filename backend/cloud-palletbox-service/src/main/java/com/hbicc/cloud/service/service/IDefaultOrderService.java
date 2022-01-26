package com.hbicc.cloud.service.service;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
//import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hbicc.cloud.service.entity.DefaultOrder;
import java.util.Map;
public interface IDefaultOrderService extends IService<DefaultOrder> {
    /**
     * 查询资金流水分页数据
     *
     * @param  where
     * @param  orderBy
     * @param  page    页码
     * @param  limit   每页条数
     * @return
     */
    Map<String, Object> page(JSONObject where, JSONArray orderBy, Integer page, Integer limit);
    /**
     * 查询资金流水所有数据
     *
     * @param  where
     * @param  orderBy
     * @return
     */
    Map<String, Object> all(JSONObject where, JSONArray orderBy);
    /**
     * 添加资金流水数据
     *
     * @param  defaultOrder 资金流水.
     * @return  int
     */
    int add(DefaultOrder defaultOrder);
    /**
     * 删除资金流水数据
     *
     * @param  id 主键
     * @return  int
     */
    int delete(String id);
    /**
     * 修改资金流水数据
     *
     * @param  defaultOrder 资金流水.
     * @return  int
     */
    int update(DefaultOrder defaultOrder);
    /**
     * id查询数据
     *
     * @param  id id
     * @return  defaultOrder.
     */
    DefaultOrder info(String id);
    /**
     * 获取字段相关属性
     *
     * @param  field
     * @param  where
     * @return
     */
    Map<String, Object> param(String field, JSONObject where);
}
