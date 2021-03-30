package com.xtei.tailorsys.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


/**
 * FileName: CorsConfig
 * Author: Li Zihao
 * Date: 2021/3/2 23:27
 * Description:解决Vue与SpringBoot通信跨域问题
 */

@Configuration
public class CorsConfig {


    private CorsConfiguration corsConfig() {
        CorsConfiguration corsConfiguration=new CorsConfiguration();
        //解决Vue与SpringBoot通信跨域问题
        corsConfiguration.addAllowedOriginPattern("*");  //设置允许跨域的路径
        corsConfiguration.addAllowedHeader("*");//设置允许跨域请求的域名
        corsConfiguration.addAllowedMethod("*");//设置允许的方法
        corsConfiguration.setAllowCredentials(true);//这里：是否允许证书 不再默认开启
        corsConfiguration.setMaxAge(3600L);//跨域允许时间
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig()); //配置 可以访问的地址
        return new CorsFilter(source);
    }

}
