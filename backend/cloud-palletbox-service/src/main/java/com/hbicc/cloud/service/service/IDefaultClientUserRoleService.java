package com.hbicc.cloud.service.service;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
//import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hbicc.cloud.service.entity.DefaultClientUserRole;
import java.util.Map;
public interface IDefaultClientUserRoleService extends IService<DefaultClientUserRole> {
    /**
     * 查询角色分页数据
     *
     * @param  where
     * @param  orderBy
     * @param  page    页码
     * @param  limit   每页条数
     * @return
     */
    Map<String, Object> page(JSONObject where, JSONArray orderBy, Integer page, Integer limit);
    /**
     * 查询角色所有数据
     *
     * @param  where
     * @param  orderBy
     * @return
     */
    Map<String, Object> all(JSONObject where, JSONArray orderBy);
    /**
     * 添加角色数据
     *
     * @param  defaultClientUserRole 角色.
     * @return  int
     */
    int add(DefaultClientUserRole defaultClientUserRole);
    /**
     * 删除角色数据
     *
     * @param  id 主键
     * @return  int
     */
    int delete(String id);
    /**
     * 修改角色数据
     *
     * @param  defaultClientUserRole 角色.
     * @return  int
     */
    int update(DefaultClientUserRole defaultClientUserRole);
    /**
     * id查询数据
     *
     * @param  id id
     * @return  defaultClientUserRole.
     */
    DefaultClientUserRole info(String id);
    /**
     * 获取字段相关属性
     *
     * @param  field
     * @param  where
     * @return
     */
    Map<String, Object> param(String field, JSONObject where);
}
