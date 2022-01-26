package com.hbicc.cloud.service.service;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
//import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hbicc.cloud.service.entity.DefaultClientUser;
import java.util.Map;
public interface IDefaultClientUserService extends IService<DefaultClientUser> {
    /**
     * 查询管理员分页数据
     *
     * @param  where
     * @param  orderBy
     * @param  page    页码
     * @param  limit   每页条数
     * @return
     */
    Map<String, Object> page(JSONObject where, JSONArray orderBy, Integer page, Integer limit);
    /**
     * 查询管理员所有数据
     *
     * @param  where
     * @param  orderBy
     * @return
     */
    Map<String, Object> all(JSONObject where, JSONArray orderBy);
    /**
     * 添加管理员数据
     *
     * @param  defaultClientUser 管理员.
     * @return  int
     */
    int add(DefaultClientUser defaultClientUser);
    /**
     * 删除管理员数据
     *
     * @param  id 主键
     * @return  int
     */
    int delete(String id);
    /**
     * 修改管理员数据
     *
     * @param  defaultClientUser 管理员.
     * @return  int
     */
    int update(DefaultClientUser defaultClientUser);
    /**
     * id查询数据
     *
     * @param  id id
     * @return  defaultClientUser.
     */
    DefaultClientUser info(String id);
    /**
     * 获取字段相关属性
     *
     * @param  field
     * @param  where
     * @return
     */
    Map<String, Object> param(String field, JSONObject where);
}
