package com.hbicc.cloud.service.service;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
//import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hbicc.cloud.service.entity.DefaultClientUserNode;
import java.util.Map;
public interface IDefaultClientUserNodeService extends IService<DefaultClientUserNode> {
    /**
     * 查询节点分页数据
     *
     * @param  where
     * @param  orderBy
     * @param  page    页码
     * @param  limit   每页条数
     * @return
     */
    Map<String, Object> page(JSONObject where, JSONArray orderBy, Integer page, Integer limit);
    /**
     * 查询节点所有数据
     *
     * @param  where
     * @param  orderBy
     * @return
     */
    Map<String, Object> all(JSONObject where, JSONArray orderBy);
    /**
     * 添加节点数据
     *
     * @param  defaultClientUserNode 节点.
     * @return  int
     */
    int add(DefaultClientUserNode defaultClientUserNode);
    /**
     * 删除节点数据
     *
     * @param  id 主键
     * @return  int
     */
    int delete(String id);
    /**
     * 修改节点数据
     *
     * @param  defaultClientUserNode 节点.
     * @return  int
     */
    int update(DefaultClientUserNode defaultClientUserNode);
    /**
     * id查询数据
     *
     * @param  id id
     * @return  defaultClientUserNode.
     */
    DefaultClientUserNode info(String id);
    /**
     * 获取字段相关属性
     *
     * @param  field
     * @param  where
     * @return
     */
    Map<String, Object> param(String field, JSONObject where);
}
