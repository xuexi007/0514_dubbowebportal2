package com.offcn.filter;

import com.offcn.util.JwtUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LoginFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //1、获取token
        String token = request.getParameter("jwt");
        //2、校验token
        Map<String, Object> map = JwtUtil.validateToken(token);
        //判断token是否有效
        if(map==null){
            //返回一个错误响应
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"token is validate");
            return;
        }

        //3、获取username
        String username = (String) map.get("username");

        //4、传递登录用户名给后面请求 api
        request.setAttribute("LOGIN_USER",username);

        //5、放行过滤器链条
        filterChain.doFilter(request,response);
    }
}
