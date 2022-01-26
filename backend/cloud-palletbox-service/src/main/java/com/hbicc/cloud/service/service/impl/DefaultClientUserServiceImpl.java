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
import com.hbicc.cloud.service.entity.DefaultClient;
import com.hbicc.cloud.service.mapper.DefaultClientMapper;
import com.hbicc.cloud.service.entity.DefaultClientUser;
import com.hbicc.cloud.service.mapper.DefaultClientUserMapper;
import com.hbicc.cloud.service.service.IDefaultClientUserService;
import com.hbicc.cloud.service.utils.MyUtil;
import com.hbicc.cloud.common.utils.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Service
public class DefaultClientUserServiceImpl extends ServiceImpl<DefaultClientUserMapper, DefaultClientUser> implements IDefaultClientUserService {


    @Autowired
    private DefaultClientMapper defaultClientMapper;


    @Override
    public Map<String, Object> page(JSONObject where, JSONArray orderBy, Integer page, Integer limit) {
        Map<String, Object> result = new LinkedHashMap<>();
        IPage<DefaultClientUser> wherePage = new Page<>(page, limit);
        QueryWrapper<DefaultClientUser> queryDefaultClientUser = null;
        queryDefaultClientUser = SearchUtil.parseWhereSql(where);
        if(orderBy.size() < 1){
            queryDefaultClientUser.orderByDesc("id");
        }else if(orderBy.size() == 2){
            if("DESC".equals(orderBy.getStr(1))){
                queryDefaultClientUser.orderByDesc(orderBy.getStr(0));
            }else{
                queryDefaultClientUser.orderByAsc(orderBy.getStr(0));
            }
        }
        IPage<DefaultClientUser> re = baseMapper.selectPage(wherePage, queryDefaultClientUser);
        List<DefaultClientUser> records = re.getRecords();
        result.put("records", records);
        Long total = re.getTotal();
        result.put("total", total);
        return result;
    }

    @Override
    public Map<String, Object> all(JSONObject where, JSONArray orderBy) {
        Map<String, Object> result = new LinkedHashMap<>();
        // 获取数据
        QueryWrapper<DefaultClientUser> queryDefaultClientUser = null;
        queryDefaultClientUser = SearchUtil.parseWhereSql(where);
        if(orderBy.size() < 1){
            queryDefaultClientUser.orderByDesc("id");
        }else if(orderBy.size() == 2){
            if("DESC".equals(orderBy.getStr(1))){
                queryDefaultClientUser.orderByDesc(orderBy.getStr(0));
            }else{
                queryDefaultClientUser.orderByAsc(orderBy.getStr(0));
            }
        }
        List<DefaultClientUser> records = baseMapper.selectList(queryDefaultClientUser);
        result.put("records", records);
        return result;
    }

    @Override
    public int add(DefaultClientUser defaultClientUser) {
        // 主键
        defaultClientUser.setCtime(DateUtil.now());
        return baseMapper.insert(defaultClientUser);
    }

    @Override
    public int delete(String id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public int update(DefaultClientUser defaultClientUser) {
        return baseMapper.updateById(defaultClientUser);
    }

    @Override
    public DefaultClientUser info(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public Map<String, Object> param(String field, JSONObject where) {
        if ("client_id".equals(field)) {
            QueryWrapper<DefaultClient> queryWrapper = null;
            queryWrapper = SearchUtil.parseWhereSql(where);
            Map<String, Object> result = new LinkedHashMap<String, Object>();
            defaultClientMapper.selectList(queryWrapper).forEach((e) -> {
                result.put(e.getId().toString(), e.getName());
            });
            return result;
        }
        if ("status".equals(field)) {
            Map<String, Object> result = new LinkedHashMap<String, Object>();
            result.put("0", "禁用");
            result.put("1", "正常");
            return result;
        }
        return null;
    }
}
