package com.xtei.tailorsys.mapper.VO;

import com.xtei.tailorsys.model.VO.FabricStockInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface FabricStockInfoVOMapper {

    int insert(FabricStockInfoVO record);

    List<FabricStockInfoVO> selectAll(@Param("fabricname") String fabricname,
                                      @Param("fabrictype") Integer fabrictype,
                                      @Param("ordertype") String ordertype,
                                      @Param("orderfield") String orderfield,
                                      @Param("min") Double min,
                                      @Param("max") Double max);

    FabricStockInfoVO selectByFasId(Integer fasId);

    //统计页面用到查询
    List<Map> getFasStockGroupByFabricType();

    List<Map> getQuantityGroupedByFabricType();
}