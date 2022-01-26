package com.hbicc.cloud.service.utils;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.hbicc.cloud.common.utils.ToolUtil;
import com.hbicc.cloud.service.config.AliProperties;
import com.hbicc.cloud.service.config.WxProperties;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.io.FileInputStream;
import java.security.PrivateKey;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

/**
 * 各种封装的方法
 */
@Component
public class MyUtil {

    @Autowired
    public static AliProperties aliProperties;
    @Autowired
    public static WxProperties wxPproperties;
    @Autowired
    public static StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setAliProperties(AliProperties aliProperties) {
        MyUtil.aliProperties = aliProperties;
    }
    @Autowired
    public void setWxProperties(WxProperties wxPproperties) {
        MyUtil.wxPproperties = wxPproperties;
    }
    @Autowired
    public void setRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        MyUtil.stringRedisTemplate = stringRedisTemplate;
    }
    public static StringRedisTemplate redis() {
        return MyUtil.stringRedisTemplate;
    }
    /**
     * 生成ID
     */
    public static String makeId(String tblName) {
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        // String valuePre = tblName.substring(0, 1).toUpperCase();
        if ("activity".equals(tblName)) {
            return snowflake.nextId() + "";
        } else if ("device".equals(tblName)) {
            return "D" + getUUID(tblName, 5);
        } else if ("client".equals(tblName)) {
            return "C" + getUUID(tblName, 5);
        } else if ("client_area".equals(tblName)) {
            return "A" + getUUID(tblName, 8);
        } else if ("device".equals(tblName)) {
            return "IGO" + getUUID(tblName, 5);
        } else if ("device_model".equals(tblName)) {
            return "M" + getUUID(tblName, 2);
        } else if ("device_qrcode".equals(tblName)) {
            return "A" + getUUID(tblName, 5);
        } else if ("device_sn".equals(tblName)) {
            return "SN" + getUUID(tblName, 5);
        } else if ("product".equals(tblName)) {
            return "P" + getUUID(tblName, 5);
        } else if ("product_cate".equals(tblName)) {
            return "C" + getUUID(tblName, 2);
        }
        return snowflake.nextId() + "";
    }
    private static String getUUID(String tblName, Integer len) {
        String key = StrUtil.format("uuid:{}", tblName);
        Long v = stringRedisTemplate.opsForValue().increment(key);
        return ToolUtil.strLeftPad(ToolUtil.tenTo36(v.intValue()), len);
    }
    /**
     * 获取精确到秒的时间戳
     *
     * @param  date
     * @return
     */
    public static int getSecondTimestamp(Date date) {
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime() / 1000);
        return Integer.valueOf(timestamp);
    }
    /**
     * 生成随机字符串
     * @param  length
     * @return
     */
    public static String getRandomString(int length) {
        // 定义一个字符串（A-Z，a-z，0-9）即62位；
        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        // 由Random生成随机数
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        // 长度为几就循环几次
        for (int i = 0; i < length; ++i) {
            // 产生0-61的数字
            int number = random.nextInt(62);
            // 将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        // 将承载的字符转换成字符串
        return sb.toString();
    }
    /**
     * 数字 截两位小数
     *
     * @param  in
     * @return
     */
    public static Double doubleFormat(Double in) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(in));
    }
    /**
     * JSONObject 合并, 相同项 求和
     *
     * @param  beforeList
     * @param  transList
     * @return
     */
    public static JSONObject comboObject(JSONObject beforeList, JSONObject transList) {
        JSONObject resultList = JSONUtil.createObj();
        for (String pid : beforeList.keySet()) {
            int nums = beforeList.getInt(pid);
            if (transList.containsKey(pid)) {
                if (nums + transList.getInt(pid) > 0) {
                    resultList.set(pid, nums + transList.getInt(pid));
                }
            } else {
                resultList.set(pid, nums);
            }
        }
        return resultList;
    }
    /**
     * JSONObject 合并, 相同项 求差
     */
    public static JSONObject diffObject(JSONObject beforeList, JSONObject afterList) {
        JSONObject resultList = JSONUtil.createObj();
        for (String pid : afterList.keySet()) {
            int nums = afterList.getInt(pid);
            if (beforeList.containsKey(pid)) {
                resultList.set(pid, nums - beforeList.getInt(pid));
            } else {
                resultList.set(pid, nums);
            }
        }
        for (String pid : beforeList.keySet()) {
            int nums = beforeList.getInt(pid);
            if (!resultList.containsKey(pid)) {
                resultList.set(pid, -1 * nums);
            }
        }
        JSONObject resultList2 = JSONUtil.createObj();
        for (String pid : resultList.keySet()) {
            int nums = resultList.getInt(pid);
            if (nums != 0) {
                resultList2.set(pid, nums);
            }
        }
        return resultList2;
    }
    /**
     * JSONObject 各子项 求和
     *
     * @param  dataList
     * @return
     */
    public static int countObject(JSONObject dataList) {
        int c = 0;
        for (String key : dataList.keySet()) {
            c = c + dataList.getInt(key);
        }
        return c;
    }
    /**
     * JSONArray 合并
     *
     * @param  beforeList
     * @param  transList
     * @return
     */
    public static JSONArray comboArray(JSONArray beforeList, JSONArray transList) {
        JSONArray result = JSONUtil.createArray();
        for (Object v : beforeList) {
            if (!result.contains(v)) {
                result.add(v);
            }
        }
        for (Object v : transList) {
            if (!result.contains(v)) {
                result.add(v);
            }
        }
        return result;
    }
    /**
     * 获取 2维 JSONArray 某字段的和
     *
     * @param  in
     * @param  field
     * @return
     */
    public static int countArray(JSONArray in, String field) {
        int c = 0;
        for (Object v : in) {
            JSONObject vi = (JSONObject) v;
            c = c + vi.getInt(field);
        }
        return c;
    }
    /**
     * 获取 2维 JSONArray 某字段的列表
     *
     * @param  in
     * @param  field
     * @return
     */
    public static JSONArray arrayColumn(JSONArray in, String field) {
        JSONArray result = JSONUtil.createArray();
        for (Object v : in) {
            JSONObject vi = (JSONObject) v;
            result.add(vi.get(field));
        }
        return result;
    }
    /**
     * 获取 2维 JSONArray 某两字段的键值对, 如果值键名为null, 则为当前对象
     *
     * @param  in
     * @param  kField
     * @param  vField
     * @return
     */
    public static JSONObject objectColumn(JSONArray in, String kField, String vField) {
        JSONObject result = JSONUtil.createObj();
        for (Object v : in) {
            JSONObject vi = (JSONObject) v;
            if (vField != null) {
                result.set(vi.getStr(kField), vi.get(vField));
            } else {
                result.set(vi.getStr(kField), vi);
            }
        }
        return result;
    }
    public static JSONObject objectTwoColumn(JSONArray in, String kField1, String kField2, String vField) {
        JSONObject result = JSONUtil.createObj();
        for (Object v : in) {
            JSONObject vi = (JSONObject) v;
            if (vField != null) {
                result.set(vi.getStr(kField1) + "_" + vi.getStr(kField2), vi.get(vField));
            } else {
                result.set(vi.getStr(kField1) + "_" + vi.getStr(kField2), vi);
            }
        }
        return result;
    }
    /**
     * 获取支付宝 sdk client
     *
     * @return
     */
    public static AlipayClient getAlipayClient() {
        String appId = aliProperties.getAppId();
        String gateway = aliProperties.getGateway();
        String yourPrivateKey = aliProperties.getYourPrivateKey();
        String alipayPublicKey = aliProperties.getAlipayPublicKey();
        AlipayClient alipayClient = new DefaultAlipayClient(
                gateway,
                appId,
                yourPrivateKey,
                "json",
                "UTF-8",
                alipayPublicKey,
                "RSA2");
        return alipayClient;
    }
    /**
     * 获取微信支付 V3 sdk client
     *
     * @return
     */
    public static HttpClient getWechatClient() {
        // 获取 mchPrivateKey
        PrivateKey mchPrivateKey;
        try {
            mchPrivateKey = PemUtil.loadPrivateKey(
                    new FileInputStream(wxPproperties.getMchkeyPath()));
            AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
                    new WechatPay2Credentials(wxPproperties.getMchId(),
                            new PrivateKeySigner(wxPproperties.getMchSerialNumber(), mchPrivateKey)),
                    wxPproperties.getApiV3Key().getBytes("utf-8"));
            WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create()
                    .withMerchant(
                            wxPproperties.getMchId(),
                            wxPproperties.getMchSerialNumber(),
                            mchPrivateKey)
                    .withValidator(new WechatPay2Validator(verifier));
            // ... 接下来，你仍然可以通过builder设置各种参数，来配置你的HttpClient
            // 通过WechatPayHttpClientBuilder构造的HttpClient，会自动的处理签名和验签
            HttpClient httpClient = builder.build();
            return httpClient;
        } catch (Exception e) {
        }
        return null;
    }
    /**
     * 获取微信小程序 sdk client
     *
     * @return
     */
    public static WxMaService getWxMaservice() {
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(wxPproperties.getAppId());
        config.setSecret(wxPproperties.getAppSecret());
        WxMaService service = new WxMaServiceImpl();
        service.setWxMaConfig(config);
        return service;
    }
}
