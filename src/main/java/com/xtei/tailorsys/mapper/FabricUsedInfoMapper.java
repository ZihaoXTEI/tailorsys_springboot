package com.xtei.tailorsys.mapper;

import com.xtei.tailorsys.model.FabricUsedInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FabricUsedInfoMapper {

    int deleteByPrimaryKey(Integer fauId);

    int insert(FabricUsedInfo record);

    FabricUsedInfo selectByPrimaryKey(Integer fauId);

    List<FabricUsedInfo> selectAll();

    int updateByPrimaryKey(FabricUsedInfo record);
}