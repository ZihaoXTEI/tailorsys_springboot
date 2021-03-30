package com.xtei.tailorsys.mapper;

import com.xtei.tailorsys.model.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface PermissionMapper {

/*    int deleteByPrimaryKey(Integer perId);

    int insert(Permission record);

    Permission selectByPrimaryKey(Integer perId);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);*/

    List<Permission> getMenu();
}