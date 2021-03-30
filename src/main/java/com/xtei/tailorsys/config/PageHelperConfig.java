package com.xtei.tailorsys.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * FileName: PageHelperConfig
 * Author: Li Zihao
 * Date: 2021/3/4 10:18
 * Description: MyBatis的PageHelper分页插件备注类
 */

@Configuration
public class PageHelperConfig {

    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        //offsetAsPageNum:设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用.
        properties.setProperty("offsetAsPageNum", "true");
        //rowBoundsWithCount:设置为true时，使用RowBounds分页会进行count查询.
        properties.setProperty("rowBoundsWithCount", "true");
        //reasonable：启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页。
        properties.setProperty("reasonable", "true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
