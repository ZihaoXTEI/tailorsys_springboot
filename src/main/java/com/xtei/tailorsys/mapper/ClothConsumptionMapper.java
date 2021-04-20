package com.xtei.tailorsys.mapper;

import com.xtei.tailorsys.entity.ClothConsumption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClothConsumptionMapper {

    int deleteByPrimaryKey(Integer consumId);

    int insert(ClothConsumption record);

    ClothConsumption selectByPrimaryKey(Integer consumId);

    ClothConsumption selectByClothtypeIdAndConsumWidth(@Param("clothtypeId") Integer clothtypeId,@Param("consumWidth") Double consumWidth);

    List<ClothConsumption> selectAll(String query);

    int updateByPrimaryKey(ClothConsumption record);

    List<Map<String, String>> selectColumnInfo();
}