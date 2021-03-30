package com.xtei.tailorsys.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Properties;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

/**
 * FileName: MyCommentGenerator
 * Author: Li Zihao
 * Date: 2021/2/10 15:30
 * Description: 自己实现的注释生成器
 */
public class MyCommentGenerator extends DefaultCommentGenerator {

    private boolean suppressAllComments;
    private boolean addRemarkComments;

    // 设置用户配置的参数
    @Override
    public void addConfigurationProperties(Properties properties) {
        // 调用父类方法保证分类方法可以正常使用
        super.addConfigurationProperties(properties);
        // 获取suppressAllComments参数值
        suppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
        // 获取addRemarkComments参数值
        addRemarkComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));
    }

    // 给字段添加注释信息
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // 如果阻止生成所有注释，直接返回
        if (suppressAllComments) {
            return;
        }
        // 文档开始注释
        field.addJavaDocLine("/**");
        // 获取数据库字段的备注信息
        String remarks = introspectedColumn.getRemarks();
        // 根据参数和备注信息判断是否添加备注信息
        if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            for (String remarkLine : remarkLines) {
                field.addJavaDocLine(" * " + remarkLine);
            }
        }
        // 注释中保留数据库字段名
        field.addJavaDocLine(" * 数据库列名：" + introspectedColumn.getActualColumnName());
        field.addJavaDocLine(" */");
    }
}
