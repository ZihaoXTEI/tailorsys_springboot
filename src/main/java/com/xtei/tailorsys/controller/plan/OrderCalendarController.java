package com.xtei.tailorsys.controller.plan;

import com.xtei.tailorsys.model.Event;
import com.xtei.tailorsys.model.Order;
import com.xtei.tailorsys.model.response.ResponseBean;
import com.xtei.tailorsys.service.OrderService;
import com.xtei.tailorsys.service.PlanService;
import com.xtei.tailorsys.util.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @Resource
    private PlanService planService;
    @Resource
    private OrderService orderService;

    @GetMapping("/event")
    public ResponseBean getEventList(){
        try{
            List<Event> eventList = planService.getAllEvent();
            return ResponseBean.success("获取数据成功",eventList);
        }catch (Exception e){
            return ResponseBean.error("获取数据错误",HttpStatus.SERIOUS_ERROR);
        }
    }

    @GetMapping("/order")
    public ResponseBean getOrderList(){
        try {
            List<Order> orderList = orderService.getAllOrderList();
            return ResponseBean.success("获取数据成功",orderList);
        }catch (Exception e){
            return ResponseBean.error("获取数据错误");
        }
    }

    @PostMapping("/")
    public ResponseBean addEvent(@RequestBody Event event){
        try {
            if(planService.addEvent(event) == 1){
                return ResponseBean.success("添加事件成功");
            }else {
                return ResponseBean.error("添加事件失败");
            }
        }catch (Exception e){
            return ResponseBean.error("添加事件错误",HttpStatus.SERIOUS_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseBean updateEvent(@PathVariable("id")Integer id,@RequestBody Event event){
        Event eventV =event;
        eventV.setId(id);
        if(planService.updateEvent(eventV) == 1){
            return ResponseBean.success("修改事件成功");
        } else {
            return ResponseBean.error("修改事件失败");
        }
    }

    @DeleteMapping("/{id}")
    //RequestMapping(value = "/",method = RequestMethod.DELETE)
    public ResponseBean deleteEvent(@PathVariable("id")Integer id){
        try{
            if(planService.deleteEvent(id) == 1){
                return ResponseBean.success("删除事件成功");
            }else {
                return ResponseBean.error("删除事件失败");
            }
        }catch (Exception e){
            return ResponseBean.error("删除事件错误",HttpStatus.SERIOUS_ERROR);
        }
    }


}
