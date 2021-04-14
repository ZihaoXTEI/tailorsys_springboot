package com.xtei.tailorsys.controller.plan;

import com.xtei.tailorsys.entity.Event;
import com.xtei.tailorsys.entity.Order;
import com.xtei.tailorsys.entity.response.ResponseBean;
import com.xtei.tailorsys.service.OrderService;
import com.xtei.tailorsys.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * FileName: OrderCalendarController
 * Author: Li Zihao
 * Date: 2021/3/31 13:02
 * Description:
 */
@RestController
@RequestMapping("plan/ordercalendar")
public class OrderCalendarController {

    @Autowired
    private PlanService planService;
    @Autowired
    private OrderService orderService;

    /**
     * 获取所有事件信息
     */
    @GetMapping("/event")
    public ResponseBean getEventList() {
        try {
            List<Event> eventList = planService.getAllEvent();
            return ResponseBean.success("获取事件信息成功", HttpServletResponse.SC_OK, eventList);
        } catch (Exception e) {
            return ResponseBean.error("获取事件信息错误", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取所有订单信息
     */
    @GetMapping("/order")
    public ResponseBean getOrderList() {
        try {
            List<Order> orderList = orderService.getAllOrderList();
            return ResponseBean.success("获取订单数据成功", HttpServletResponse.SC_OK, orderList);
        } catch (Exception e) {
            return ResponseBean.error("获取订单数据错误", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseBean addEvent(@RequestBody Event event) {
        try {
            planService.addEvent(event);
            return ResponseBean.success("添加事件信息成功", HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            return ResponseBean.error("添加事件信息错误", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseBean updateEvent(@PathVariable("id") Integer id, @RequestBody Event event) {
        Event eventV = event;
        eventV.setId(id);
        try {
            planService.updateEvent(eventV);
            return ResponseBean.success("修改事件信息成功", HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("修改事件信息失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    //RequestMapping(value = "/",method = RequestMethod.DELETE)
    public ResponseBean deleteEvent(@PathVariable("id") Integer id) {
        try {
            planService.deleteEvent(id);
            return ResponseBean.success("删除事件信息成功", HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("删除事件信息错误", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
