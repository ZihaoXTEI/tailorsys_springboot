package com.xtei.tailorsys.mapper;

import com.xtei.tailorsys.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    Order selectByPrimaryKey(String orderId);

    List<Order> selectAll();

    int updateByPrimaryKey(Order record);

    int updateOrderStatus(@Param("orderId")String orderId,@Param("orderStatus") Integer orderStatus);

    int getNumberOfOrder();

    int getNumberOfIncompleteOrder();

    List<Map> getInfoOfIncompleteOrder();
}