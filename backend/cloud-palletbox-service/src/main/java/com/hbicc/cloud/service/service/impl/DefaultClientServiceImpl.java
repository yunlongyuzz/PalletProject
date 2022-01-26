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
import com.hbicc.cloud.service.service.IDefaultClientService;
import com.hbicc.cloud.service.utils.MyUtil;
import com.hbicc.cloud.common.utils.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Service
public class DefaultClientServiceImpl extends ServiceImpl<DefaultClientMapper, DefaultClient> implements IDefaultClientService {

    @Override
    public Map<String, Object> page(JSONObject where, JSONArray orderBy, Integer page, Integer limit) {
        Map<String, Object> result = new LinkedHashMap<>();
        IPage<DefaultClient> wherePage = new Page<>(page, limit);
        QueryWrapper<DefaultClient> queryDefaultClient = null;
        queryDefaultClient = SearchUtil.parseWhereSql(where);
        if(orderBy.size() < 1){
            queryDefaultClient.orderByDesc("id");
        }else if(orderBy.size() == 2){
            if("DESC".equals(orderBy.getStr(1))){
                queryDefaultClient.orderByDesc(orderBy.getStr(0));
            }else{
                queryDefaultClient.orderByAsc(orderBy.getStr(0));
            }
        }
        IPage<DefaultClient> re = baseMapper.selectPage(wherePage, queryDefaultClient);
        List<DefaultClient> records = re.getRecords();
        result.put("records", records);
        Long total = re.getTotal();
        result.put("total", total);
        return result;
    }


    @Override
    public Map<String, Object> all(JSONObject where, JSONArray orderBy) {
        Map<String, Object> result = new LinkedHashMap<>();
        // 获取数据
        QueryWrapper<DefaultClient> queryDefaultClient = null;
        queryDefaultClient = SearchUtil.parseWhereSql(where);
        if(orderBy.size() < 1){
            queryDefaultClient.orderByDesc("id");
        }else if(orderBy.size() == 2){
            if("DESC".equals(orderBy.getStr(1))){
                queryDefaultClient.orderByDesc(orderBy.getStr(0));
            }else{
                queryDefaultClient.orderByAsc(orderBy.getStr(0));
            }
        }
        List<DefaultClient> records = baseMapper.selectList(queryDefaultClient);
        result.put("records", records);
        return result;
    }




    @Override
    public int add(DefaultClient defaultClient) {
        // 主键
        //defaultClient.setId(IdUtil.objectId());
        if (StrUtil.isEmpty(defaultClient.getId())) {
            defaultClient.setId(MyUtil.makeId("client"));
        }
        defaultClient.setCreatedAt(DateUtil.now());
        return baseMapper.insert(defaultClient);
    }
    @Override
    public int delete(String id) {
        return baseMapper.deleteById(id);
    }
    @Override
    public int update(DefaultClient defaultClient) {
        defaultClient.setUpdatedAt(DateUtil.now());
        return baseMapper.updateById(defaultClient);
    }

    @Override
    public DefaultClient info(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public Map<String, Object> param(String field, JSONObject where) {
        if ("roles".equals(field)) {
            Map<String, Object> result = new LinkedHashMap<String, Object>();
            result.put("TUOPAN", "提供托盘");
            result.put("FACTORY", "工厂");
            result.put("BIZ1", "一级经销商");
            result.put("BIZ2", "二级经销商");
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