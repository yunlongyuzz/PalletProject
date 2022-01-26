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
import com.hbicc.cloud.service.entity.DefaultPalletLog;
import com.hbicc.cloud.service.mapper.DefaultPalletLogMapper;
import com.hbicc.cloud.service.service.IDefaultPalletLogService;
import com.hbicc.cloud.service.utils.MyUtil;
import com.hbicc.cloud.common.utils.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Service
public class DefaultPalletLogServiceImpl extends ServiceImpl<DefaultPalletLogMapper, DefaultPalletLog> implements IDefaultPalletLogService {
    @Autowired
    private DefaultClientMapper defaultClientMapper;
    @Autowired
    private DefaultClientUserMapper defaultClientUserMapper;
    @Override
    public Map<String, Object> page(JSONObject where, JSONArray orderBy, Integer page, Integer limit) {
        Map<String, Object> result = new LinkedHashMap<>();
        IPage<DefaultPalletLog> wherePage = new Page<>(page, limit);
        QueryWrapper<DefaultPalletLog> queryDefaultPalletLog = null;
        queryDefaultPalletLog = SearchUtil.parseWhereSql(where);
        if(orderBy.size() < 1){
            queryDefaultPalletLog.orderByDesc("id");
        }else if(orderBy.size() == 2){
            if("DESC".equals(orderBy.getStr(1))){
                queryDefaultPalletLog.orderByDesc(orderBy.getStr(0));
            }else{
                queryDefaultPalletLog.orderByAsc(orderBy.getStr(0));
            }
        }
        IPage<DefaultPalletLog> re = baseMapper.selectPage(wherePage, queryDefaultPalletLog);
        List<DefaultPalletLog> records = re.getRecords();
        result.put("records", records);
        Long total = re.getTotal();
        result.put("total", total);
        return result;
    }
    @Override
    public Map<String, Object> all(JSONObject where, JSONArray orderBy) {
        Map<String, Object> result = new LinkedHashMap<>();
        // 获取数据
        QueryWrapper<DefaultPalletLog> queryDefaultPalletLog = null;
        queryDefaultPalletLog = SearchUtil.parseWhereSql(where);
        if(orderBy.size() < 1){
            queryDefaultPalletLog.orderByDesc("id");
        }else if(orderBy.size() == 2){
            if("DESC".equals(orderBy.getStr(1))){
                queryDefaultPalletLog.orderByDesc(orderBy.getStr(0));
            }else{
                queryDefaultPalletLog.orderByAsc(orderBy.getStr(0));
            }
        }
        List<DefaultPalletLog> records = baseMapper.selectList(queryDefaultPalletLog);
        result.put("records", records);
        return result;
    }
    @Override
    public int add(DefaultPalletLog defaultPalletLog) {
        // 主键
        defaultPalletLog.setCreatedAt(DateUtil.now());
        return baseMapper.insert(defaultPalletLog);
    }
    @Override
    public int delete(String id) {
        return baseMapper.deleteById(id);
    }
    @Override
    public int update(DefaultPalletLog defaultPalletLog) {
        defaultPalletLog.setUpdatedAt(DateUtil.now());
        return baseMapper.updateById(defaultPalletLog);
    }
    @Override
    public DefaultPalletLog info(String id) {
        return baseMapper.selectById(id);
    }
    @Override
    public Map<String, Object> param(String field, JSONObject where) {
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
        if ("trans_type".equals(field)) {
            Map<String, Object> result = new LinkedHashMap<String, Object>();
            result.put("S1", "S1：满托入库");
            result.put("S2", "S2：满托出库");
            result.put("S3", "S3：空托入库");
            result.put("S4", "S4：空托出库");
            result.put("S5", "S5：满托出厂");
            result.put("S6", "S6：空托回厂");
            return result;
        }
        if ("user_id".equals(field)) {
            QueryWrapper<DefaultClientUser> queryWrapper = null;
            queryWrapper = SearchUtil.parseWhereSql(where);
            Map<String, Object> result = new LinkedHashMap<String, Object>();
            defaultClientUserMapper.selectList(queryWrapper).forEach((e) -> {
                result.put(e.getId().toString(), e.getNickname());
            });
            return result;
        }
        return null;
    }
}