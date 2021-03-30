package com.xtei.tailorsys.mapper.VO;

import com.xtei.tailorsys.model.VO.ClothConsumptionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClothConsumptionVOMapper {

    int insert(ClothConsumptionVO record);

    List<ClothConsumptionVO> selectAll(String query);

    ClothConsumptionVO selectByConsumId(Integer consumId);
}