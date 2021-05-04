package com.xtei.tailorsys.config.jwt;

import com.xtei.tailorsys.util.JwtTokenUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * FileName: JwtAuthenticationSuccessHandler
 * Author: Li Zihao
 * Date: 2021/3/2 14:44
 * Description: 登录成功处理类
 */

@Component("JwtAuthenticationSuccessHandler")
public class JwtAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal != null && principal instanceof UserDetails) {
            UserDetails user = (UserDetails) principal;
            httpServletRequest.getSession().setAttribute("userDetail", user);
            String role = "";
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            for (GrantedAuthority authority : authorities){
                role = authority.getAuthority();
            }

            String token = JwtTokenUtils.createToken(user.getUsername(), role, true);

            System.out.println("登录成功 => 用户名："+ user.getUsername());
//        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), false);
            // 返回创建成功的token
            // 但是这里创建的token只是单纯的token
            // 按照jwt的规定，最后请求的时候应该是 `Bearer token`

            httpServletResponse.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);
            httpServletResponse.setContentType("application/json;charset=utf-8");
            PrintWriter out = httpServletResponse.getWriter();
            //out.write("{\"status\":400,\"message\":\"用户名或密码错误\"}");
            out.write("{\"" +
                    "data\": {" +
                    "\"username\": \"" + user.getUsername() + "\"," +
                    " \"token\": \"" + JwtTokenUtils.TOKEN_PREFIX + token  +
                    "\"}," +
                    "\"message\": \"登录成功\"," +
                    " \"status\": 200" +
                    "}");
            out.flush();
            out.close();
            /*httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json; charset=utf-8");
            httpServletResponse.setHeader("token",JwtTokenUtils.TOKEN_PREFIX + token);*/

        }
    }
}
