package com.xtei.tailorsys.mapper.VO;

import com.xtei.tailorsys.entity.VO.FabricReceiveInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FabricReceiveInfoVOMapper {

    List<FabricReceiveInfoVO> selectAll(String query);
}