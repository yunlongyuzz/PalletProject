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
import com.hbicc.cloud.service.entity.DefaultClientUserRole;
import com.hbicc.cloud.service.mapper.DefaultClientUserRoleMapper;
import com.hbicc.cloud.service.service.IDefaultClientUserRoleService;
import com.hbicc.cloud.service.utils.MyUtil;
import com.hbicc.cloud.common.utils.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Service
public class DefaultClientUserRoleServiceImpl extends ServiceImpl<DefaultClientUserRoleMapper, DefaultClientUserRole> implements IDefaultClientUserRoleService {
    @Override
    public Map<String, Object> page(JSONObject where, JSONArray orderBy, Integer page, Integer limit) {
        Map<String, Object> result = new LinkedHashMap<>();
        IPage<DefaultClientUserRole> wherePage = new Page<>(page, limit);
        QueryWrapper<DefaultClientUserRole> queryDefaultClientUserRole = null;
        queryDefaultClientUserRole = SearchUtil.parseWhereSql(where);
        if(orderBy.size() < 1){
            queryDefaultClientUserRole.orderByDesc("id");
        }else if(orderBy.size() == 2){
            if("DESC".equals(orderBy.getStr(1))){
                queryDefaultClientUserRole.orderByDesc(orderBy.getStr(0));
            }else{
                queryDefaultClientUserRole.orderByAsc(orderBy.getStr(0));
            }
        }
        IPage<DefaultClientUserRole> re = baseMapper.selectPage(wherePage, queryDefaultClientUserRole);
        List<DefaultClientUserRole> records = re.getRecords();
        result.put("records", records);
        Long total = re.getTotal();
        result.put("total", total);
        return result;
    }
    @Override
    public Map<String, Object> all(JSONObject where, JSONArray orderBy) {
        Map<String, Object> result = new LinkedHashMap<>();
        // 获取数据
        QueryWrapper<DefaultClientUserRole> queryDefaultClientUserRole = null;
        queryDefaultClientUserRole = SearchUtil.parseWhereSql(where);
        if(orderBy.size() < 1){
            queryDefaultClientUserRole.orderByDesc("id");
        }else if(orderBy.size() == 2){
            if("DESC".equals(orderBy.getStr(1))){
                queryDefaultClientUserRole.orderByDesc(orderBy.getStr(0));
            }else{
                queryDefaultClientUserRole.orderByAsc(orderBy.getStr(0));
            }
        }
        List<DefaultClientUserRole> records = baseMapper.selectList(queryDefaultClientUserRole);
        result.put("records", records);
        return result;
    }
    @Override
    public int add(DefaultClientUserRole defaultClientUserRole) {
        // 主键
        return baseMapper.insert(defaultClientUserRole);
    }
    @Override
    public int delete(String id) {
        return baseMapper.deleteById(id);
    }
    @Override
    public int update(DefaultClientUserRole defaultClientUserRole) {
        return baseMapper.updateById(defaultClientUserRole);
    }
    @Override
    public DefaultClientUserRole info(String id) {
        return baseMapper.selectById(id);
    }
    @Override
    public Map<String, Object> param(String field, JSONObject where) {
        return null;
    }
}