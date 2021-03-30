package com.xtei.tailorsys.generator;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * FileName: Generator
 * Author: Li Zihao
 * Date: 2021/2/10 14:43
 * Description: Mybatis代码生成器
 */
public class Generator {

    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        // MyBatis Generator执行过程中的警告信息
        List<String> warnings = new ArrayList<String>();
        // 当生成的代码重复时，覆盖原代码
        boolean overwrite = true;
        // 读取MyBatis Generator的配置文件
        InputStream inputStream = Generator.class.getResourceAsStream("/mybatis-generator.xml");
        ConfigurationParser configurationParser = new ConfigurationParser(warnings);
        Configuration config = configurationParser.parseConfiguration(inputStream);
        inputStream.close();

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        // 创建MyBatisGenerator
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        // 执行生产代码
        myBatisGenerator.generate(null);
        // 输出警告信息
        for(String warning : warnings){
            System.out.println(warning);
        }
        System.out.println("代码生成成功");

    }

}
