package org.example.utils;

import com.alibaba.fastjson2.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.example.pojo.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * jwt令牌解析工具类
 */
@Component
public class JwtUtils {
    private static final String SECRET_KEY="BigEvent";

    public String genorateJwt(Map<String,Object> claims){
        String Jwt=JWT.create()
                .withClaim("user",claims)//装载claim
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))//设置过期时间
                .sign(Algorithm.HMAC512(SECRET_KEY));//解析密匙
        return Jwt;
    }

    public User parseJwt(String jwt){
        Verification required = JWT.require(Algorithm.HMAC512(SECRET_KEY));
        DecodedJWT verify = required.build().verify(jwt);
        Claim userJson = verify.getClaim("user");
        User user= JSON.parseObject(String.valueOf(userJson),User.class);
        return user;
    }
}
