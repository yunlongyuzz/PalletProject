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
import com.hbicc.cloud.service.entity.DefaultCar;
import com.hbicc.cloud.service.mapper.DefaultCarMapper;
import com.hbicc.cloud.service.service.IDefaultCarService;
import com.hbicc.cloud.service.utils.MyUtil;
import com.hbicc.cloud.common.utils.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Service
public class DefaultCarServiceImpl extends ServiceImpl<DefaultCarMapper, DefaultCar> implements IDefaultCarService {
    @Override
    public Map<String, Object> page(JSONObject where, JSONArray orderBy, Integer page, Integer limit) {


        Map<String, Object> result = new LinkedHashMap<>();
        IPage<DefaultCar> wherePage = new Page<>(page, limit);
        QueryWrapper<DefaultCar> queryDefaultCar = null;
        queryDefaultCar = SearchUtil.parseWhereSql(where);
        if(orderBy.size() < 1){
            queryDefaultCar.orderByDesc("id");
        }else if(orderBy.size() == 2){
            if("DESC".equals(orderBy.getStr(1))){
                queryDefaultCar.orderByDesc(orderBy.getStr(0));
            }else{
                queryDefaultCar.orderByAsc(orderBy.getStr(0));
            }
        }
        IPage<DefaultCar> re = baseMapper.selectPage(wherePage, queryDefaultCar);
        List<DefaultCar> records = re.getRecords();
        result.put("records", records);
        Long total = re.getTotal();
        result.put("total", total);
        return result;
    }
    @Override
    public Map<String, Object> all(JSONObject where, JSONArray orderBy) {
        Map<String, Object> result = new LinkedHashMap<>();
        // 获取数据
        QueryWrapper<DefaultCar> queryDefaultCar = null;
        queryDefaultCar = SearchUtil.parseWhereSql(where);
        if(orderBy.size() < 1){
            queryDefaultCar.orderByDesc("id");
        }else if(orderBy.size() == 2){
            if("DESC".equals(orderBy.getStr(1))){
                queryDefaultCar.orderByDesc(orderBy.getStr(0));
            }else{
                queryDefaultCar.orderByAsc(orderBy.getStr(0));
            }
        }
        List<DefaultCar> records = baseMapper.selectList(queryDefaultCar);
        result.put("records", records);
        return result;
    }
    @Override
    public int add(DefaultCar defaultCar) {
        // 主键
        //defaultCar.setId(IdUtil.objectId());
        if (StrUtil.isEmpty(defaultCar.getId())) {
            defaultCar.setId(MyUtil.makeId("car"));
        }
        defaultCar.setCreatedAt(DateUtil.now());
        return baseMapper.insert(defaultCar);
    }
    @Override
    public int delete(String id) {
        return baseMapper.deleteById(id);
    }
    @Override
    public int update(DefaultCar defaultCar) {
        defaultCar.setUpdatedAt(DateUtil.now());
        return baseMapper.updateById(defaultCar);
    }
    @Override
    public DefaultCar info(String id) {
        return baseMapper.selectById(id);
    }
    @Override
    public Map<String, Object> param(String field, JSONObject where) {
        return null;
    }
}