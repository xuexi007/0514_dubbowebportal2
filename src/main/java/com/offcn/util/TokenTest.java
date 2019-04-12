package com.offcn.util;

import java.util.Map;

public class TokenTest {

    public static void main(String[] args) {
        //1、生产一个token

       //String token = JwtUtil.generateToken("admin");

       // System.out.println("token："+token);
        String token="Bearer eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1NTQ4MDE5MDYsInVzZXJuYW1lIjoiYWRtaW4ifQ.K8MTgUH0Fakr7_pMPN7iP46VbTPq71T-wZR5Beh0M8gjXx6Uxg21nJm0G72uhI0F6phcFgsBlOutz0LoIrnX2g";
        Map<String, Object> map = JwtUtil.validateToken(token);
       String username=(String) map.get("username");
        System.out.println("username:"+username);
    }
}
