package com.xtei.tailorsys.converter;

import com.xtei.tailorsys.entity.Order;
import com.xtei.tailorsys.entity.OrderFabricDetail;
import com.xtei.tailorsys.entity.VO.OrderVO;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * FileName: OrderConverter
 * Author: Li Zihao
 * Date: 2021/3/16 12:03
 * Description:
 */
public class OrderConverter {

    public static Order converterToOrder(OrderVO orderVO){
        Order order = new Order();
        BeanUtils.copyProperties(orderVO,order);
        return order;
    }

    public static OrderFabricDetail converterToOrderFabricDetail(OrderVO orderVO){
        OrderFabricDetail orderFabricDetail = new OrderFabricDetail();
        List<OrderFabricDetail> orderFabricDetails = orderVO.getOrderFabricDetailList();
        return orderFabricDetail;
    }

}
