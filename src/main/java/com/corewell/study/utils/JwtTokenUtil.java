package com.corewell.study.utils;

import com.corewell.study.domain.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/04/16:26
 * @Description:
 */
@Component
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据用户信息生成token
     *
     * @param account
     * @return
     */

    public String generateToken(Account account) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, account.getAccount());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);

    }

    /**
     * 从token中获取登录用户名
     *
     * @param token
     * @return
     */

    public String getUserNameFromToken(String token) {
        String userName;
        try {
            Claims claims = getClamsFromToken(token);
            userName = claims.getSubject();
        } catch (Exception e) {
            userName = null;
        }

        return userName;

    }

    /**
     * 验证token是否有限
     *
     * @param token
     * @param account
     * @return
     */
    public boolean validateToken(String token, Account account) {
        String userName = getUserNameFromToken(token);
        return userName.equals(account.getAccount()) && !isTokenExpired(token);
    }

    /**
     * 判断是否可用刷新token
     *
     * @param
     */
    public boolean canRefresh(String token) {
        return isTokenExpired(token);
    }

    /**
     * 刷新token
     *
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        Claims claims = getClamsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 根据荷载生成JWT TOKEN
     *
     * @param claims
     * @return
     */

    public String generateToken(Map<String, Object> claims) {

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.ES256, secret)
                .compact();
    }

    /**
     * 判断token是否在有效期内
     *
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expireDate = getExpiredDateFromToken(token);
        return expireDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     *
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClamsFromToken(token);
        Date expiration = claims.getExpiration();
        return expiration;

    }

    /**
     * 从token中获取荷载
     *
     * @param token
     * @return
     */
    private Claims getClamsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }


    /**
     * 生成token 失效时间
     *
     * @return
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

}
