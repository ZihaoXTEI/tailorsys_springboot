package com.xtei.tailorsys.config.jwt;

import com.xtei.tailorsys.util.JwtTokenUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * FileName: JwtAuthenticationFailHander
 * Author: Li Zihao
 * Date: 2021/3/2 15:08
 * Description: 登录失败处理类
 */

@Component("JWTAuthenticationFailHander")
public class JwtAuthenticationFailHander extends SimpleUrlAuthenticationFailureHandler {

    //用户名密码错误执行
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse, AuthenticationException e) throws IOException, ServletException, IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        // 获得用户名密码
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");

        System.out.println(username+password);

        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        //out.write("{\"status\":400,\"message\":\"用户名或密码错误\"}");
        out.write("{\"meta\": {" +
                "\"message\": \"用户名或密码错误\"," +
                " \"status\": 400" +
                "}" +
                "}");
        out.flush();
        out.close();

        //httpServletResponse.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }
}
