package com.xtei.tailorsys.mapper;

import com.xtei.tailorsys.entity.OrderFabricDetail;
import com.xtei.tailorsys.entity.VO.OrderFabricDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderFabricDetailMapper {

    int deleteByPrimaryKey(Integer ofdNo);

    int insert(OrderFabricDetail record);

    OrderFabricDetail selectByPrimaryKey(Integer ofdNo);

    List<OrderFabricDetail> selectAll();

    List<OrderFabricDetailVO> selectOrderDetailAndFabricInfo(String orderId);

    int updateByPrimaryKey(OrderFabricDetail record);
}