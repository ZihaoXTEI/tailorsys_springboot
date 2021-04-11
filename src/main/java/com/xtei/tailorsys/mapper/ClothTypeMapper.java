package com.xtei.tailorsys.mapper;

import com.xtei.tailorsys.entity.ClothType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClothTypeMapper {

    int deleteByPrimaryKey(Integer clothtypeId);

    int insert(ClothType record);

    ClothType selectByPrimaryKey(Integer clothtypeId);

    List<ClothType> selectAll(String query);

    int updateByPrimaryKey(ClothType record);

    List<Map<Integer,String>> selectClothTypeMap();

    List<Map<String,String>> selectColumnInfo();
}