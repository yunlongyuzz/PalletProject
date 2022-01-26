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
import com.hbicc.cloud.service.entity.DefaultPayRule;
import com.hbicc.cloud.service.mapper.DefaultPayRuleMapper;
import com.hbicc.cloud.service.service.IDefaultPayRuleService;
import com.hbicc.cloud.service.utils.MyUtil;
import com.hbicc.cloud.common.utils.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Service
public class DefaultPayRuleServiceImpl extends ServiceImpl<DefaultPayRuleMapper, DefaultPayRule> implements IDefaultPayRuleService {
    @Autowired
    private DefaultClientMapper defaultClientMapper;
    @Override
    public Map<String, Object> page(JSONObject where, JSONArray orderBy, Integer page, Integer limit) {
        Map<String, Object> result = new LinkedHashMap<>();
        IPage<DefaultPayRule> wherePage = new Page<>(page, limit);
        QueryWrapper<DefaultPayRule> queryDefaultPayRule = null;
        queryDefaultPayRule = SearchUtil.parseWhereSql(where);
        if(orderBy.size() < 1){
            queryDefaultPayRule.orderByDesc("id");
        }else if(orderBy.size() == 2){
            if("DESC".equals(orderBy.getStr(1))){
                queryDefaultPayRule.orderByDesc(orderBy.getStr(0));
            }else{
                queryDefaultPayRule.orderByAsc(orderBy.getStr(0));
            }
        }
        IPage<DefaultPayRule> re = baseMapper.selectPage(wherePage, queryDefaultPayRule);
        List<DefaultPayRule> records = re.getRecords();
        result.put("records", records);
        Long total = re.getTotal();
        result.put("total", total);
        return result;
    }
    @Override
    public Map<String, Object> all(JSONObject where, JSONArray orderBy) {
        Map<String, Object> result = new LinkedHashMap<>();
        // 获取数据
        QueryWrapper<DefaultPayRule> queryDefaultPayRule = null;
        queryDefaultPayRule = SearchUtil.parseWhereSql(where);
        if(orderBy.size() < 1){
            queryDefaultPayRule.orderByDesc("id");
        }else if(orderBy.size() == 2){
            if("DESC".equals(orderBy.getStr(1))){
                queryDefaultPayRule.orderByDesc(orderBy.getStr(0));
            }else{
                queryDefaultPayRule.orderByAsc(orderBy.getStr(0));
            }
        }
        List<DefaultPayRule> records = baseMapper.selectList(queryDefaultPayRule);
        result.put("records", records);
        return result;
    }
    @Override
    public int add(DefaultPayRule defaultPayRule) {
        // 主键
        defaultPayRule.setCreatedAt(DateUtil.now());
        return baseMapper.insert(defaultPayRule);
    }
    @Override
    public int delete(String id) {
        return baseMapper.deleteById(id);
    }
    @Override
    public int update(DefaultPayRule defaultPayRule) {
        defaultPayRule.setUpdatedAt(DateUtil.now());
        return baseMapper.updateById(defaultPayRule);
    }
    @Override
    public DefaultPayRule info(String id) {
        return baseMapper.selectById(id);
    }
    @Override
    public Map<String, Object> param(String field, JSONObject where) {
        if ("pallet_client_id".equals(field)) {
            QueryWrapper<DefaultClient> queryWrapper = null;
            queryWrapper = SearchUtil.parseWhereSql(where);
            Map<String, Object> result = new LinkedHashMap<String, Object>();
            defaultClientMapper.selectList(queryWrapper).forEach((e) -> {
                result.put(e.getId().toString(), e.getName());
            });
            return result;
        }
        if ("fee_type".equals(field)) {
            Map<String, Object> result = new LinkedHashMap<String, Object>();
            result.put("FEE_CHUZU", "出租费用");
            result.put("FEE_PLATFORM", "平台使用费");
            result.put("FEE_ZHILIU", "滞留费");
            result.put("FEE_WEIXIU", "维修费");
            result.put("FEE_SUNHUI", "损毁费");
            result.put("FEE_OTHER", "其他费用");
            return result;
        }
        if ("pay_client_id".equals(field)) {
            QueryWrapper<DefaultClient> queryWrapper = null;
            queryWrapper = SearchUtil.parseWhereSql(where);
            Map<String, Object> result = new LinkedHashMap<String, Object>();
            defaultClientMapper.selectList(queryWrapper).forEach((e) -> {
                result.put(e.getId().toString(), e.getName());
            });
            return result;
        }
        if ("from_client_id".equals(field)) {
            QueryWrapper<DefaultClient> queryWrapper = null;
            queryWrapper = SearchUtil.parseWhereSql(where);
            Map<String, Object> result = new LinkedHashMap<String, Object>();
            defaultClientMapper.selectList(queryWrapper).forEach((e) -> {
                result.put(e.getId().toString(), e.getName());
            });
            return result;
        }
        if ("to_client_id".equals(field)) {
            QueryWrapper<DefaultClient> queryWrapper = null;
            queryWrapper = SearchUtil.parseWhereSql(where);
            Map<String, Object> result = new LinkedHashMap<String, Object>();
            defaultClientMapper.selectList(queryWrapper).forEach((e) -> {
                result.put(e.getId().toString(), e.getName());
            });
            return result;
        }
        return null;
    }
}