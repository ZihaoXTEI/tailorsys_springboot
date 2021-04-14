package com.xtei.tailorsys.entity;

import lombok.Data;

import java.util.List;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table permission_tb
 */
@Data
public class Permission {
    /**
     * 编号
     * 数据库列名：per_id
     */
    private Integer perId;

    /**
     * 权限名称
     * 数据库列名：per_name
     */
    private String perName;

    /**
     * 权限的父ID
     * 数据库列名：per_pid
     */
    private Integer perPid;

    /**
     * 权限路径
     * 数据库列名：per_path
     */
    private String perPath;

    /**
     * 权限等级
     * 数据库列名：per_level
     */
    private Integer perLevel;

    private List<Permission> child;

    public List<Permission> getChild() {
        return child;
    }

    public void setChild(List<Permission> child) {
        this.child = child;
    }

}