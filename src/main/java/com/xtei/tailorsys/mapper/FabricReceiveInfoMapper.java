package com.xtei.tailorsys.mapper;

import com.xtei.tailorsys.model.FabricReceiveInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FabricReceiveInfoMapper {

    int deleteByPrimaryKey(Integer farId);

    int insert(FabricReceiveInfo record);

    FabricReceiveInfo selectByPrimaryKey(Integer farId);

    List<FabricReceiveInfo> selectAll(String query);

    int updateByPrimaryKey(FabricReceiveInfo record);
}