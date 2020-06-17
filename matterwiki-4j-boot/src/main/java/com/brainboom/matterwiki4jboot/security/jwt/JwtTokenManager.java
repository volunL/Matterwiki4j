package com.brainboom.matterwiki4jboot.security.jwt;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtTokenManager implements TokenManager {


    //过期时间
    private long tokenExpiration;
    //秘钥
    private String tokenSignKey;


    /**
     * 创建token
     * create token
     *
     * @param username 用户名
     */
    @Override
    public String createToken(String username) {
        String token = Jwts.builder()
                //用户名 username
                .setSubject(username)
                //过期时间 expiretime
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }


    /**
     * 验证token
     * verify token
     *
     * @param token
     */
    @Override
    public String verify(String token) {

        String user = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
        return user;



    }

}
