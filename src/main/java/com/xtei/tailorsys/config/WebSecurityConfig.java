package com.xtei.tailorsys.config;

import com.xtei.tailorsys.filter.JWTAuthorizationFilter;
import com.xtei.tailorsys.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * FileName: WebSecurityConfig
 * Author: Li Zihao
 * Date: 2021/3/2 14:16
 * Description:
 */


@CrossOrigin(origins = "*",maxAge = 3600)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailHander;

    //装载BCrypt密码编码器
    @Bean
    public PasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //@Qualifier("userServiceImpl")
    @Bean
    UserDetailsService JWTuserDetailsService(){
        UserServiceImpl userService =new UserServiceImpl();
        return userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(JWTuserDetailsService()).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*        http.antMatcher("/**").//指定登录认证的Controller
                formLogin().usernameParameter("username").passwordParameter("password").loginPage("/login").successHandler(
                authenticationSuccessHandler).failureHandler(authenticationFailHander)
                .and()
                .authorizeRequests()
                //登录相关
                //.antMatchers("/bupi/**").permitAll()
                //.antMatchers("/article/**").authenticated()
                .antMatchers("/tasks/**").hasRole("ADMIN")
                //.antMatchers(HttpMethod.POST, "/jwt/tasks/**").hasRole("USER")
                .and()//.addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));
        http.logout().permitAll();
        http.cors().and().csrf().ignoringAntMatchers("/**");*/

/*        http.cors().and().csrf().disable()
                .authorizeRequests()
                // 测试用资源，需要验证了的用户才能访问
                .antMatchers("/tasks/**")
                .authenticated()
                .antMatchers(HttpMethod.DELETE, "/tasks/**")
                .hasRole("ADMIN")
                // 其他都放行了
                .anyRequest().permitAll()
                .and()
                //.addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));*/
        http.cors().and().csrf().disable();

        http.authorizeRequests()
                //login请求无需验证
                .antMatchers("/","login","/image/**").permitAll()
                //其它请务必经过登录验证成功后才能访问
                //.anyRequest().permitAll();
                .anyRequest().authenticated();

        //授权认证
        http.formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailHander)
                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));

        http.logout().permitAll();

/*        http
                .authorizeRequests()
                .antMatchers("/home")
                .hasRole("ADMIN")

                .anyRequest().permitAll()

                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailHander)

                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));*/

    }
}
