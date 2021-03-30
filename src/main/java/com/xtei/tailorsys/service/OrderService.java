package com.xtei.tailorsys.service;


import com.xtei.tailorsys.model.Anthropometry;
import com.xtei.tailorsys.model.Order;
import com.xtei.tailorsys.model.OrderFabricDetail;
import com.xtei.tailorsys.model.OrderProcess;
import com.xtei.tailorsys.model.VO.OrderFabricDetailVO;
import com.xtei.tailorsys.model.VO.OrderViewVO;
import com.xtei.tailorsys.util.pagehelper.PageResult;

import javax.annotation.Resource;
import java.util.ArrayList;
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
}
