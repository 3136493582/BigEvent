package example;

import com.alibaba.fastjson2.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.example.pojo.User;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void JwtGenorate(){

        Map<String, Object> clams=new HashMap<>();
        clams.put("username","cs");
        clams.put("password","123456");

        String jwt= JWT.create()
                .withClaim("user",clams)
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))
                .sign(Algorithm.HMAC512("BigEvent"));

        System.out.println(jwt);
    }

    @Test
    public void parseJwt(){
        String jwt="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MzE3ODY0MTYsInVzZXIiOnsicGFzc3dvcmQiOiIxMjM0NTY3IiwidXNlcm5hbWUiOiJ4dWNoYW8ifX0.SUJOX52HlVZFJJmNsG4OpONDFr9DvdBBzadQiYW0GQGDjWXiFA6MzNpKA4obuP4OvZlRHXqJ4XeFXRp80OGNVw";
        Verification required = JWT.require(Algorithm.HMAC512("BigEvent"));
        DecodedJWT verify = required.build().verify(jwt);
        Claim userjson = verify.getClaim("user");
        User user= JSON.parseObject(String.valueOf(userjson),User.class);
        System.out.println(user);
    }

}
