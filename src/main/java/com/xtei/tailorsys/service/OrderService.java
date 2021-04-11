package com.xtei.tailorsys.service;


import com.xtei.tailorsys.entity.Order;
import com.xtei.tailorsys.entity.OrderFabricDetail;
import com.xtei.tailorsys.entity.OrderProcess;
import com.xtei.tailorsys.entity.VO.OrderFabricDetailVO;
import com.xtei.tailorsys.entity.VO.OrderViewVO;
import com.xtei.tailorsys.util.pagehelper.PageResult;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * FileName: OrderService
 * Author: Li Zihao
 * Date: 2021/3/13 21:01
 * Description:
 */

public interface OrderService {

    boolean submitOrder(Order order, List<OrderFabricDetail> orderFabricDetails);

    int addOrder(Order order);

    int updateOrder(Order order);

    int addOrderFabricDetail(OrderFabricDetail orderFabricDetail);

    int updateOrderFabricDetail(OrderFabricDetail orderFabricDetail);

    int deleteOrderFabricDetail(Integer ofdNo);

    PageResult findOrderViewList(String orderId,
                                 String customerName,
                                 Integer clothTypeId,
                                 Integer paymentMethod,
                                 Integer orderStatus,
                                 String screenField,
                                 Date[] date,
                                 String orderType,
                                 Integer pageNum,
                                 Integer pageSize);

    Order findOrderById(String orderId);

    List<OrderFabricDetailVO> findOrderDetailAndFabricInfoList(String orderId);

    int getNumberOfOrder();

    int getNumberOfIncompleteOrder();

    List<Map> getInfoOfIncompleteOrder();

    List<OrderViewVO> getAllOrder();

    List<OrderProcess> getAllOrderProcess();

    List<Order> getAllOrderList();

}
