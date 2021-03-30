package com.xtei.tailorsys.mapper;

import com.xtei.tailorsys.model.ClothConsumption;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClothConsumptionMapper {

    int deleteByPrimaryKey(Integer consumId);

    int insert(ClothConsumption record);

    ClothConsumption selectByPrimaryKey(Integer consumId);

    List<ClothConsumption> selectAll(String query);

    int updateByPrimaryKey(ClothConsumption record);

    List<Map<String, String>> selectColumnInfo();
}