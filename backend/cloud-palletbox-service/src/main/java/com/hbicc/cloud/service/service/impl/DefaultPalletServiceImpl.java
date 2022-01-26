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
import com.hbicc.cloud.service.entity.DefaultPallet;
import com.hbicc.cloud.service.mapper.DefaultPalletMapper;
import com.hbicc.cloud.service.service.IDefaultPalletService;
import com.hbicc.cloud.service.utils.MyUtil;
import com.hbicc.cloud.common.utils.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Service
public class DefaultPalletServiceImpl extends ServiceImpl<DefaultPalletMapper, DefaultPallet> implements IDefaultPalletService {
    @Autowired
    private DefaultClientMapper defaultClientMapper;
    @Override
    public Map<String, Object> page(JSONObject where, JSONArray orderBy, Integer page, Integer limit) {
        Map<String, Object> result = new LinkedHashMap<>();
        IPage<DefaultPallet> wherePage = new Page<>(page, limit);
        QueryWrapper<DefaultPallet> queryDefaultPallet = null;
        queryDefaultPallet = SearchUtil.parseWhereSql(where);
        if(orderBy.size() < 1){
            queryDefaultPallet.orderByDesc("id");
        }else if(orderBy.size() == 2){
            if("DESC".equals(orderBy.getStr(1))){
                queryDefaultPallet.orderByDesc(orderBy.getStr(0));
            }else{
                queryDefaultPallet.orderByAsc(orderBy.getStr(0));
            }
        }
        IPage<DefaultPallet> re = baseMapper.selectPage(wherePage, queryDefaultPallet);
        List<DefaultPallet> records = re.getRecords();
        result.put("records", records);
        Long total = re.getTotal();
        result.put("total", total);
        return result;
    }
    @Override
    public Map<String, Object> all(JSONObject where, JSONArray orderBy) {
        Map<String, Object> result = new LinkedHashMap<>();
        // 获取数据
        QueryWrapper<DefaultPallet> queryDefaultPallet = null;
        queryDefaultPallet = SearchUtil.parseWhereSql(where);
        if(orderBy.size() < 1){
            queryDefaultPallet.orderByDesc("id");
        }else if(orderBy.size() == 2){
            if("DESC".equals(orderBy.getStr(1))){
                queryDefaultPallet.orderByDesc(orderBy.getStr(0));
            }else{
                queryDefaultPallet.orderByAsc(orderBy.getStr(0));
            }
        }
        List<DefaultPallet> records = baseMapper.selectList(queryDefaultPallet);
        result.put("records", records);
        return result;
    }
    @Override
    public int add(DefaultPallet defaultPallet) {
        // 主键
        //defaultPallet.setId(IdUtil.objectId());
        if (StrUtil.isEmpty(defaultPallet.getId())) {
            defaultPallet.setId(MyUtil.makeId("pallet"));
        }
        defaultPallet.setCreatedAt(DateUtil.now());
        return baseMapper.insert(defaultPallet);
    }
    @Override
    public int delete(String id) {
        return baseMapper.deleteById(id);
    }
    @Override
    public int update(DefaultPallet defaultPallet) {
        defaultPallet.setUpdatedAt(DateUtil.now());
        return baseMapper.updateById(defaultPallet);
    }
    @Override
    public DefaultPallet info(String id) {
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
        if ("cur_client_id".equals(field)) {
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
            result.put("0", "初始化");
            result.put("1", "正常");
            result.put("2", "损坏");
            result.put("4", "损毁");
            return result;
        }
        return null;
    }
}