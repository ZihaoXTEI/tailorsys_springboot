package com.xtei.tailorsys.mapper;

import com.xtei.tailorsys.model.FabricType;
import com.xtei.tailorsys.model.VO.FabricTypeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface FabricTypeMapper {

    int deleteByPrimaryKey(Integer fabrictypeId);

    int insert(FabricType record);

    FabricType selectByPrimaryKey(Integer fabrictypeId);

    List<FabricType> selectAll(@Param("fabrictypeName") String fabrictypeName,@Param("fabrictypeCategory") String fabrictypeCategory);

    int updateByPrimaryKey(FabricType record);

    //List<Map<Integer,String>> selectFabricTypeMap();
    List<FabricTypeVO> selectFabricTypeMap();
}