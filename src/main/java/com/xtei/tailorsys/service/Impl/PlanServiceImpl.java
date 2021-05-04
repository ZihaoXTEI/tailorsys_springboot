package com.xtei.tailorsys.service.Impl;

import com.xtei.tailorsys.mapper.EventMapper;
import com.xtei.tailorsys.mapper.OrderMapper;
import com.xtei.tailorsys.mapper.OrderProcessMapper;
import com.xtei.tailorsys.entity.Event;
import com.xtei.tailorsys.entity.OrderProcess;
import com.xtei.tailorsys.service.PlanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * FileName: PlanServiceImpl
 * Author: Li Zihao
 * Date: 2021/3/24 12:51
 * Description:
 */
@Service("planService")
@Transactional(rollbackFor = Exception.class)
public class PlanServiceImpl implements PlanService {

    @Resource
    private OrderProcessMapper orderProcessMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private EventMapper eventMapper;

    @Override
    public List<OrderProcess> getOrderProcessList(String orderId) {
        List<OrderProcess> orderProcessList = orderProcessMapper.selectAll(orderId);
        return orderProcessList;
    }

    /**
     * 订单状态码
     * -1:作废
     * 1：新创建状态
     * 2：完成裁剪
     * 3：完成缝纫
     * 4：完成整烫
     * 5：成衣
     * 6：完成交付
     */
    @Override
    public int updateOrderStatus(String orderId, String userName, Date dateTime){
        int oldOrderStatus = orderMapper.selectByPrimaryKey(orderId).getOrderStatus();
        OrderProcess orderProcess = orderProcessMapper.selectByOrderId(orderId);
        if(oldOrderStatus == 1){
            orderProcess.setTailoring(userName);
            orderProcess.setTailoringDate(dateTime);
        }else if(oldOrderStatus == 2){
            orderProcess.setSewing(userName);
            orderProcess.setSewingDate(dateTime);
        }else if(oldOrderStatus == 3){
            orderProcess.setIroning(userName);
            orderProcess.setIroningDate(dateTime);
        }else if(oldOrderStatus == 4){
            orderProcess.setFinish(userName);
            orderProcess.setFinishDate(dateTime);
        }else if(oldOrderStatus == 5 || oldOrderStatus == 6){
            return 0;
        }else {
            return -1;
        }

        int res1 = orderProcessMapper.updateByOrderId(orderProcess);
        int res2 = orderMapper.updateOrderStatus(orderId,oldOrderStatus +1);
        return res1 +res2;
    }

    @Override
    public int addEvent(Event event){
        int res = eventMapper.insert(event);
        return res;
    }

    @Override
    public int updateEvent(Event event){
        int res = eventMapper.updateByPrimaryKey(event);
        return res;
    }

    @Override
    public int deleteEvent(Integer id){
        int res = eventMapper.deleteByPrimaryKey(id);
        return res;
    }

    @Override
    public List<Event> getAllEvent(){
        List<Event> eventList = eventMapper.selectAll();
        return eventList;
    }
}
