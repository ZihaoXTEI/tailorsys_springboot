package com.xtei.tailorsys.mapper;

import com.xtei.tailorsys.entity.OrderProcess;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderProcessMapper {

    int deleteByPrimaryKey(Integer oprId);

    int insert(OrderProcess record);

    OrderProcess selectByPrimaryKey(Integer oprId);

    OrderProcess selectByOrderId(String orderId);

    List<OrderProcess> selectAll(String orderId);

    int updateByOrderId(OrderProcess record);

}