package com.offcn.util;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    static final String SECRET="ThisIsASecret";
    /**
     * 生成token
     */
    public static String generateToken(String username){

        //1、把用户名封装到一个map集合
        Map<String, Object> map = new HashMap<>();
        map.put("username",username);
        //2、生成token
        String token = Jwts.builder().setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + 3000000L))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return "Bearer "+token;
    }

    /**
     * 校验token
     */
    public static Map<String, Object> validateToken(String token){

        Map<String, Object> map = null;
        try {
            map = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace("Bearer ", "")).getBody();
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            System.out.println("token过期");
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return map;
    }
}
