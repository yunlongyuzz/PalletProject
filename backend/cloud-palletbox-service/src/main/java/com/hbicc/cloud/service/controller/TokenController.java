package com.hbicc.cloud.service.controller;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.hutool.core.lang.Console;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipayEncrypt;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.github.rexsheng.mybatis.mapper.DynamicMapper;
import com.hbicc.cloud.common.utils.JwtUtil;
import com.hbicc.cloud.common.utils.ResponseUtil;
import com.hbicc.cloud.service.config.AliProperties;
import com.hbicc.cloud.service.utils.MyUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
// import lombok.extern.slf4j.Slf4j;
// @Slf4j
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private DynamicMapper myMapper;
    @Autowired
    private AliProperties aliProperties;


    /**
     * Admin's token获取
     * @param    json
     * @return
     */
    @PostMapping("admin/get")
    public ResponseUtil adminGet(@RequestBody String json) {
        JSONObject accountObj = JSONUtil.parseObj(json);
        String account = accountObj.getStr("account");
        String password = accountObj.getStr("password");
        Map<String, Object> userInfo = new HashMap<String, Object>();

        String SQL = "SELECT `user_id`, `user_name`, `phone`, `nickname` FROM `admin` WHERE `user_name` = #{account} AND `password` = md5(#{password}) AND `is_valid` = 1";
        Map<String, Object> param = new HashMap<>();
            param.put("account", account);
            param.put("password", password);

        List<Map<String, Object>> userList = myMapper.selectByMapWithParams(SQL, param);
        if (userList.size() < 1) {
            return ResponseUtil.out(null);
        }
        userInfo = userList.get(0);


        Map<String, Object> claims = MapUtil.builder(new HashMap<String, Object>())
                .put("id",userInfo.get("user_id"))
                .put("nickname",userInfo.get("nickname"))
                .put("type",userInfo.get("user_name"))
                .build();
        String token = null;
        try {
            token = JwtUtil.createJWT(claims);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //把token和uuid放入到userinfo中去
        userInfo.put("token", token);
        userInfo.put("uuid", userInfo.get("user_id"));
        return ResponseUtil.out(userInfo);
    }


    /**
     * client token 获取
     *
     * @param    json
     * @return
     */
    @PostMapping("client/get")
    public ResponseUtil clientGet(@RequestBody String json) {

        //把传过来的json字符串转化为json对象
        JSONObject accountObj = JSONUtil.parseObj(json);
        String enName = accountObj.getStr("en_name");
        String account = accountObj.getStr("account");
        String password = accountObj.getStr("password");

        Map<String, Object> userInfo = new HashMap<String, Object>();

        //根据获取的json信息去数据库查询clientUser的用户
        String SQL = "(SELECT 0 AS uid, id AS client_id, '超管' AS nickname, '' AS mobile, 'all' AS role FROM `client` WHERE supername = #{account} AND `superpwd` = MD5(#{password}) AND enname = #{en_name} AND `status` = 1) UNION (SELECT cl.id AS uid, cl.client_id, cl.nickname, cl.mobile, cl.role FROM `client_user` cl LEFT JOIN `client` c ON cl.`client_id` = c.`id` WHERE c.enname = #{en_name} AND cl.`username` = #{account} AND cl.`password` = MD5(#{password}) AND cl.`status` = 1)";
        Map<String, Object> param = new HashMap<>();
            param.put("en_name", enName);
            param.put("account", account);
            param.put("password", password);
        List<Map<String, Object>> userList = myMapper.selectByMapWithParams(SQL, param);

        //可能查到多个，取第一个
        if (userList.size() < 1) {
            return ResponseUtil.out(null);
        }
        userInfo = userList.get(0);

        //把查到的用户信息放到另外一个map里面
        Map<String, Object> claims = MapUtil.builder(new HashMap<String, Object>())
                .put("id", userInfo.get("uid"))
                .put("nickname", userInfo.get("nickname"))
                .put("client_id", userInfo.get("client_id"))
                .put("role", userInfo.get("role"))
                .put("type", "client")
                .build();

        //创建token令牌
        String token = null;
        try {
            token = JwtUtil.createJWT(claims);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //把token令牌放入到map里面
        userInfo.put("token", token);
        userInfo.put("uuid", userInfo.get("uid"));
        return ResponseUtil.out(userInfo);


    }




    /**
     * 微信小程序 token 获取
     *
     * @param    json
     * @return
     */
    @PostMapping("mobile/wechat/get")
    public ResponseUtil mobileWechatGet(@RequestBody    String json) {
        JSONObject accountObj = JSONUtil.parseObj(json);
        String code = accountObj.getStr("code");
        String sessionKey;
        String openId;
        /* log.info("==============================================code: {}", code); */
        try {
            WxMaJscode2SessionResult result = MyUtil.getWxMaservice().getUserService().getSessionInfo(code);
            sessionKey = result.getSessionKey();
            openId = result.getOpenid();
        } catch (WxErrorException e) {
            /* e.printStackTrace(); */
            return ResponseUtil.error(-99, "", null);
        }
        if (sessionKey == null || openId == null) {
            return ResponseUtil.error(-99, "", null);
        }
        /* 数据处理过程 */
        // Map<String, Object> userInfo = tokenService.getWechatUserByOpenid(openId);
        // if (userInfo == null) {
        //     /* 如果数据不存在的话 */
        //     try {
        //         String userId = MyUtil.makeId("user");
        //         userInfo = tokenService.insertAndGetWechatUser(userId, openId);
        //     } catch (RuntimeException e) {
        //         return ResponseUtil.error(-1, e.getMessage(), null);
        //     }
        // }
        Map<String, Object> result = MapUtil.builder(new HashMap<String, Object>()).build();
        // result.put("user", userInfo);
        // try {
        //     Map<String, Object> claims = MapUtil.builder(new HashMap<String, Object>())
        //             .put("id", userInfo.get("user_id"))
        //             .put("nick_name", userInfo.get("nick_name"))
        //             .put("mobile", userInfo.get("mobile"))
        //             .put("type", "mobile")
        //             .build();
        //     String token = JwtUtil.createJWT(claims);
        //     result.put("token", token);
        //     result.put("session_key", sessionKey);
        // } catch (Exception e) {
        //     return ResponseUtil.error(-1, e.getMessage(), null);
        // }
        return ResponseUtil.success(1, "", result);
    }
    /**
     * 微信获取手机号
     *
     * @param    json
     * @return
     */
    @PostMapping("mobile/wechat/updateMobile")
    public ResponseUtil mobileWechatUpdateMobile(@RequestBody    String json, @RequestHeader("user-id") String userId) {
        JSONObject accountObj = JSONUtil.parseObj(json);
        String sessionKey = accountObj.getStr("session_key");
        String encryptedData = accountObj.getJSONObject("detail").getStr("encryptedData");
        String iv = accountObj.getJSONObject("detail").getStr("iv");
        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = MyUtil.getWxMaservice().getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
        // Map<String, Object> userInfo = tokenService.updateWechatUserMobile(userId, phoneNoInfo);
        Map<String, Object> result = MapUtil.builder(new HashMap<String, Object>()).build();
        // result.put("user", userInfo);
        // try {
        //     Map<String, Object> claims = MapUtil.builder(new HashMap<String, Object>())
        //             .put("id", userInfo.get("user_id")).put("nick_name", userInfo.get("nick_name"))
        //             .put("mobile", userInfo.get("mobile")).build();
        //     String token = JwtUtil.createJWT(claims);
        //     result.put("token", token);
        //     result.put("session_key", sessionKey);
        // } catch (Exception e) {
        //     return ResponseUtil.error(-1, e.getMessage(), null);
        // }
        return ResponseUtil.success(1, "", result);
    }
    /**
     * 更新微信用户信息
     *
     * @param    json
     * @return
     */
    @PostMapping("mobile/wechat/updateUserInfo")
    public ResponseUtil mobileWechatUpdateUserInfo(@RequestBody    String json, @RequestHeader("user-id") String userId) {
        JSONObject accountObj = JSONUtil.parseObj(json);
        String sessionKey = accountObj.getStr("session_key");
        String encryptedData = accountObj.getJSONObject("detail").getStr("encryptedData");
        String iv = accountObj.getJSONObject("detail").getStr("iv");
        // 解密用户信息
        WxMaUserInfo userInfoWx = MyUtil.getWxMaservice().getUserService().getUserInfo(sessionKey, encryptedData, iv);
        // Map<String, Object> userInfo = tokenService.updateWechatUserInfo(userId, userInfoWx);
        Map<String, Object> result = MapUtil.builder(new HashMap<String, Object>()).build();
        // result.put("user", userInfo);
        // try {
        //     Map<String, Object> claims = MapUtil.builder(new HashMap<String, Object>())
        //             .put("id", userInfo.get("user_id")).put("nick_name", userInfo.get("nick_name"))
        //             .put("mobile", userInfo.get("mobile")).build();
        //     String token = JwtUtil.createJWT(claims);
        //     result.put("token", token);
        //     result.put("session_key", sessionKey);
        // } catch (Exception e) {
        //     return ResponseUtil.error(-1, e.getMessage(), null);
        // }
        return ResponseUtil.success(1, "", result);
    }
    /**
     * 支付宝小程序 token 获取
     *
     * @param    json
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("mobile/alipay/get")
    public ResponseUtil mobileAlipayGet(@RequestBody    String json) {
        JSONObject obj = JSONUtil.parseObj(json);
        String code = obj.getStr("code");
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setGrantType("authorization_code");
        request.setCode(code);
        AlipaySystemOauthTokenResponse response;
        try {
            response = MyUtil.getAlipayClient().execute(request);
            if (response.isSuccess()) {
                String accessToken = response.getAccessToken();
                String alipayUserId = response.getAlipayUserId();
                String userId = response.getUserId();
                // 查找是否有这个用户
                // DefaultUserInfoAlipayMini userInfo = defaultUserInfoAlipayMiniService.getOne(
                //         new QueryWrapper<DefaultUserInfoAlipayMini>()
                //                 .eq("user_id", userId),
                //         false);
                // if (userInfo == null) {
                //     // 没有找到用户的话, 创建用户
                //     String id = MyUtil.makeId("user");
                //     defaultUserInfoAlipayMiniService.add(new DefaultUserInfoAlipayMini()
                //             .setMyUserId(id)
                //             .setAvatar("")
                //             .setCity("")
                //             .setNickName("")
                //             .setAlipayUserId(alipayUserId)
                //             .setUserId(userId)
                //             .setProvince("")
                //             .setMobile("")
                //             .setXtoken("unauthorized")
                //             .setCtime(DateUtil.now())
                //     );
                //     defaultUserService.add(new DefaultUser()
                //             .setCtime(DateUtil.now())
                //             .setUserId(id)
                //             .setFrom("ALIPAY_M")
                //             .setMobile("")
                //             .setNickName("")
                //     );
                //     userInfo = defaultUserInfoAlipayMiniService.getOne(
                //             new QueryWrapper<DefaultUserInfoAlipayMini>()
                //                     .eq("user_id", userId),
                //             false);
                // }
                // JSONObject userInfoResult = JSONUtil.createObj()
                //         .set("avatar", userInfo.getAvatar())
                //         // .set("mobile", userInfo.getMobile())
                //         .set("mobile", "暂无")
                //         .set("nick_name", userInfo.getNickName())
                //         .set("status", userInfo.getXtoken())
                //         .set("user_id", userInfo.getMyUserId());
                JSONObject result = JSONUtil.createObj();
                // result.set("user", userInfoResult);
                // try {
                //     Map<String, Object> claims = MapUtil.builder(new HashMap<String, Object>())
                //             .put("id", userInfoResult.getStr("user_id"))
                //             .put("nick_name", userInfoResult.getStr("nick_name"))
                //             .put("mobile", userInfoResult.getStr("mobile"))
                //             .put("type", "mobile")
                //             .build();
                //     String token = JwtUtil.createJWT(claims);
                //     result.set("token", token);
                //     result.set("session_key", accessToken);
                // } catch (Exception e) {
                //     return ResponseUtil.error(-1, e.getMessage(), null);
                // }
                return ResponseUtil.success(1, "", result);
            } else {
                return ResponseUtil.error(-1, "", null);
            }
        } catch (AlipayApiException e) {
            return ResponseUtil.error(-1, "", null);
        }
    }
    /**
     * 获取支付宝 用户信息 更新到用户表
     *
     * @param    json
     * @param    userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("mobile/alipay/updateUserInfo")
    public ResponseUtil mobileAlipayUpdateUserInfo(@RequestBody    String json, @RequestHeader("user-id") String userId) {
        JSONObject obj = JSONUtil.parseObj(json);
        JSONObject userInfoPost = obj.getJSONObject("userInfo");
        String accessToken = obj.getStr("session_key");
        // defaultUserInfoAlipayMiniService.update(new UpdateWrapper<DefaultUserInfoAlipayMini>()
        //         .eq("my_user_id", userId)
        //         .set("avatar", userInfoPost.getStr("avatar"))
        //         .set("city", userInfoPost.getStr("city"))
        //         .set("nick_name", userInfoPost.getStr("nickName"))
        //         .set("province", userInfoPost.getStr("province"))
        //         .set("xtoken", "authorized")
        // );
        // defaultUserService.update(new UpdateWrapper<DefaultUser>()
        //         .eq("user_id", userId)
        //         .set("nick_name", userInfoPost.getStr("nickName"))
        // );
        // DefaultUserInfoAlipayMini userInfo = defaultUserInfoAlipayMiniService.getOne(
        //         new QueryWrapper<DefaultUserInfoAlipayMini>()
        //                 .eq("my_user_id", userId),
        //         false);
        // JSONObject userInfoResult = JSONUtil.createObj()
        //         .set("avatar", userInfo.getAvatar())
        //         // .set("mobile", userInfo.getMobile())
        //         .set("mobile", "暂无")
        //         .set("nick_name", userInfo.getNickName())
        //         .set("status", userInfo.getXtoken())
        //         .set("user_id", userInfo.getMyUserId());
        JSONObject result = JSONUtil.createObj();
        // result.set("user", userInfoResult);
        // try {
        //     Map<String, Object> claims = MapUtil.builder(new HashMap<String, Object>())
        //             .put("id", userInfoResult.getStr("user_id"))
        //             .put("nick_name", userInfoResult.getStr("nick_name"))
        //             .put("mobile", userInfoResult.getStr("mobile"))
        //             .put("type", "mobile")
        //             .build();
        //     String token = JwtUtil.createJWT(claims);
        //     result.set("token", token);
        //     result.set("session_key", accessToken);
        // } catch (Exception e) {
        //     return ResponseUtil.error(-1, e.getMessage(), null);
        // }
        return ResponseUtil.success(1, "", result);
    }
    /**
     * 获取支付宝 用户手机号 更新到用户表
     *
     * @param    json
     * @param    userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("mobile/alipay/updateMobile")
    public ResponseUtil mobileAlipayUpdateMobile(@RequestBody    String json, @RequestHeader("user-id") String userId) {
        JSONObject obj = JSONUtil.parseObj(json);
        String accessToken = obj.getStr("session_key");
        JSONObject detail = obj.getJSONObject("detail");
        // String sign = detail.getStr("sign");
        String content = detail.getStr("response");
        String decryptKey = aliProperties.getAesKey();
        // String signType = "RSA2";
        String charset = "UTF-8";
        String encryptType = "AES";
        try {
            String plainData = null;
            plainData = AlipayEncrypt.decryptContent(content, encryptType, decryptKey, charset);
            // {
            // "code": "10000",
            // "msg": "Success",
            // "mobile": "1597671905"
            // }
            JSONObject res = JSONUtil.parseObj(plainData);
            if (res.getStr("code").equals("10000")) {
                String mobile = res.getStr("mobile");
                // defaultUserInfoAlipayMiniService.update(new UpdateWrapper<DefaultUserInfoAlipayMini>()
                //         .eq("my_user_id", userId)
                //         .set("mobile", mobile)
                // );
                // defaultUserService.update(new UpdateWrapper<DefaultUser>()
                //         .eq("user_id", userId)
                //         .set("mobile", mobile)
                // );
                // DefaultUserInfoAlipayMini userInfo = defaultUserInfoAlipayMiniService.getOne(
                //         new QueryWrapper<DefaultUserInfoAlipayMini>()
                //                 .eq("my_user_id", userId),
                //         false);
                // JSONObject userInfoResult = JSONUtil.createObj()
                //         .set("avatar", userInfo.getAvatar())
                //         // .set("mobile", userInfo.getMobile())
                //         .set("mobile", "暂无")
                //         .set("nick_name", userInfo.getNickName())
                //         .set("status", userInfo.getXtoken())
                //         .set("user_id", userInfo.getMyUserId());
                // JSONObject result = JSONUtil.createObj();
                // result.set("user", userInfoResult);
                // try {
                //     Map<String, Object> claims = MapUtil.builder(new HashMap<String, Object>())
                //             .put("id", userInfoResult.getStr("user_id"))
                //             .put("nick_name", userInfoResult.getStr("nick_name"))
                //             .put("mobile", userInfoResult.getStr("mobile"))
                //             .put("type", "mobile")
                //             .build();
                //     String token = JwtUtil.createJWT(claims);
                //     result.set("token", token);
                //     result.set("session_key", accessToken);
                //     return ResponseUtil.success(1, "", result);
                // } catch (Exception e) {
                //     return ResponseUtil.error(-1, e.getMessage(), null);
                // }
                return ResponseUtil.success(0, res.getStr("msg"), null);
            } else {
                return ResponseUtil.success(0, res.getStr("msg"), null);
            }
        } catch (AlipayApiException e) {
            // 解密异常, 记录日志
            return ResponseUtil.success(-1, "解密异常", null);
        }
    }
}
