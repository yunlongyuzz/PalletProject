package com.hbicc.cloud.service.service;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
//import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hbicc.cloud.service.entity.DefaultPallet;
import java.util.Map;
public interface IDefaultPalletService extends IService<DefaultPallet> {
    /**
     * 查询托盘分页数据
     *
     * @param  where
     * @param  orderBy
     * @param  page    页码
     * @param  limit   每页条数
     * @return
     */
    Map<String, Object> page(JSONObject where, JSONArray orderBy, Integer page, Integer limit);
    /**
     * 查询托盘所有数据
     *
     * @param  where
     * @param  orderBy
     * @return
     */
    Map<String, Object> all(JSONObject where, JSONArray orderBy);
    /**
     * 添加托盘数据
     *
     * @param  defaultPallet 托盘.
     * @return  int
     */
    int add(DefaultPallet defaultPallet);
    /**
     * 删除托盘数据
     *
     * @param  id 主键
     * @return  int
     */
    int delete(String id);
    /**
     * 修改托盘数据
     *
     * @param  defaultPallet 托盘.
     * @return  int
     */
    int update(DefaultPallet defaultPallet);
    /**
     * id查询数据
     *
     * @param  id id
     * @return  defaultPallet.
     */
    DefaultPallet info(String id);
    /**
     * 获取字段相关属性
     *
     * @param  field
     * @param  where
     * @return
     */
    Map<String, Object> param(String field, JSONObject where);
}
