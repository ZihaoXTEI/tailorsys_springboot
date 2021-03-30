package com.xtei.tailorsys.mapper;

import com.xtei.tailorsys.model.FabricInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FabricInfoMapper {

    int deleteByPrimaryKey(Integer fabricId);

    int insert(FabricInfo record);

    FabricInfo selectByPrimaryKey(Integer fabricId);

    List<FabricInfo> selectAll();

    int updateByPrimaryKey(FabricInfo record);

    List<Map<Integer,String>> selectByName(String name);

    List<Map<Integer,String>> selectFabricInfoMap(String query);

    int getNumberOfFabricInfo();

}