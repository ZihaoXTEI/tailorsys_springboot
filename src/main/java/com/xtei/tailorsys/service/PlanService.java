package com.xtei.tailorsys.service;

import com.xtei.tailorsys.entity.Event;
import com.xtei.tailorsys.entity.OrderProcess;

import java.util.Date;
import java.util.List;

/**
 * FileName: PlanService
 * Author: Li Zihao
 * Date: 2021/3/24 12:50
 * Description:
 */

public interface PlanService {

    List<OrderProcess> getOrderProcessList(String orderId);

    int updateOrderStatus(String orderId, String userName, Date dateTime);

    int addEvent(Event event);

    int updateEvent(Event event);

    int deleteEvent(Integer id);

    List<Event> getAllEvent();
}
