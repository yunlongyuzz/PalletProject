package com.hbicc.cloud.service.service.impl;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.date.DateUtil;
//import cn.hutool.core.util.IdUtil;
//import cn.hutool.core.lang.Snowflake;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbicc.cloud.service.entity.DefaultClientUserNode;
import com.hbicc.cloud.service.mapper.DefaultClientUserNodeMapper;
import com.hbicc.cloud.service.service.IDefaultClientUserNodeService;
import com.hbicc.cloud.service.utils.MyUtil;
import com.hbicc.cloud.common.utils.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Service
public class DefaultClientUserNodeServiceImpl extends ServiceImpl<DefaultClientUserNodeMapper, DefaultClientUserNode> implements IDefaultClientUserNodeService {
    @Override
    public Map<String, Object> page(JSONObject where, JSONArray orderBy, Integer page, Integer limit) {
        Map<String, Object> result = new LinkedHashMap<>();
        IPage<DefaultClientUserNode> wherePage = new Page<>(page, limit);
        QueryWrapper<DefaultClientUserNode> queryDefaultClientUserNode = null;
        queryDefaultClientUserNode = SearchUtil.parseWhereSql(where);
        if(orderBy.size() < 1){
            queryDefaultClientUserNode.orderByDesc("id");
        }else if(orderBy.size() == 2){
            if("DESC".equals(orderBy.getStr(1))){
                queryDefaultClientUserNode.orderByDesc(orderBy.getStr(0));
            }else{
                queryDefaultClientUserNode.orderByAsc(orderBy.getStr(0));
            }
        }
        IPage<DefaultClientUserNode> re = baseMapper.selectPage(wherePage, queryDefaultClientUserNode);
        List<DefaultClientUserNode> records = re.getRecords();
        result.put("records", records);
        Long total = re.getTotal();
        result.put("total", total);
        return result;
    }
    @Override
    public Map<String, Object> all(JSONObject where, JSONArray orderBy) {
        Map<String, Object> result = new LinkedHashMap<>();
        // 获取数据
        QueryWrapper<DefaultClientUserNode> queryDefaultClientUserNode = null;
        queryDefaultClientUserNode = SearchUtil.parseWhereSql(where);
        if(orderBy.size() < 1){
            queryDefaultClientUserNode.orderByDesc("id");
        }else if(orderBy.size() == 2){
            if("DESC".equals(orderBy.getStr(1))){
                queryDefaultClientUserNode.orderByDesc(orderBy.getStr(0));
            }else{
                queryDefaultClientUserNode.orderByAsc(orderBy.getStr(0));
            }
        }
        List<DefaultClientUserNode> records = baseMapper.selectList(queryDefaultClientUserNode);
        result.put("records", records);
        return result;
    }
    @Override
    public int add(DefaultClientUserNode defaultClientUserNode) {
        // 主键
        return baseMapper.insert(defaultClientUserNode);
    }
    @Override
    public int delete(String id) {
        return baseMapper.deleteById(id);
    }
    @Override
    public int update(DefaultClientUserNode defaultClientUserNode) {
        return baseMapper.updateById(defaultClientUserNode);
    }
    @Override
    public DefaultClientUserNode info(String id) {
        return baseMapper.selectById(id);
    }
    @Override
    public Map<String, Object> param(String field, JSONObject where) {
        return null;
    }
}