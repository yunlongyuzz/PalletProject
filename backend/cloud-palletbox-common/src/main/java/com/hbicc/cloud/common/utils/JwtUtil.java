package com.hbicc.cloud.common.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * jwt登录
 */
public class JwtUtil {

    // 密钥 -- 根据实际项目，这里可以做成配置
    public static final String KEY = "0s9l6xCowiuzRwXClFfyXQ00Trn0y88sk8sP0aYp";

    //置签名使用的签名算法和签名使用的秘钥
    public static Key genKey() {
        Key sKey = Keys.hmacShaKeyFor(Base64.encode(KEY).getBytes());
        return sKey;
    }

    /**
    * 创建jwt
    * @param  claims
    * @return
    * @throws  Exception
    */
    public static String createJWT(Map<String, Object> claims) throws Exception {

        JwtBuilder builder = Jwts.builder();

        // 生成JWT的时间
        long nowMillis = System.currentTimeMillis();

        //jwt的签发时间,Date类型
        Date now = new Date(nowMillis);
        builder.setIssuedAt(now);

        //设置过期时间
        long ttlMillis = 3600 * 24 * 7 * 1000;
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        String id = IdUtil.objectId();
        String issuer = "JavaJwt";
        String subject = "JavaJwt";


            builder.setClaims(claims) // 如果有私有声明。
            .setId(id) // 设置jti(JWT ID)：是JWT的唯一标识。
            .setIssuer(issuer) // issuer：jwt签发人。
            .setSubject(subject) // sub(Subject)：代表这个JWT的主体。
            .signWith(genKey()); // 设置签名使用的签名算法和签名使用的秘钥


        //将jwtBuilder转化成一个字符串
        String jwt = builder.compact();

        return jwt;

    }


    /**
    * 解密jwt
    * @param  jwt
    * @return
    * @throws  Exception
    */
    public static Map<String, Object> parseJWT(String jwt) throws Exception {
        Claims claims = Jwts.parserBuilder().setSigningKey(genKey()).build().parseClaimsJws(jwt).getBody();
        return claims;
    }


}
