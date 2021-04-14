package com.xtei.tailorsys.controller.plan;

import com.xtei.tailorsys.entity.OrderProcess;
import com.xtei.tailorsys.entity.response.ResponseBean;
import com.xtei.tailorsys.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * FileName: OrderCardController
 * Author: Li Zihao
 * Date: 2021/3/23 13:08
 * Description: 订单卡片页面视图控制器
 */
@RestController
@RequestMapping("plan")
public class OrderCardController {

    @Autowired
    private PlanService planService;

    /**
     * 获取订单流程信息列表
     */
    @GetMapping("orderprocess")
    public ResponseBean getOrderProcessList(@RequestParam(value = "orderid", defaultValue = "")String orderId){
        try {
            List<OrderProcess> orderProcessList = planService.getOrderProcessList(orderId);
            if(orderProcessList  != null){
                return ResponseBean.success("获取订单流程信息成功", HttpServletResponse.SC_OK,orderProcessList);
            }
            else {
                return ResponseBean.error("获取订单流程信息失败",HttpServletResponse.SC_NOT_FOUND);
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("获取订单流程信息失败",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }


    }

    /**
     * 更新订单状态
     *
     */
    @PutMapping("/updateorderstatus/{orderid}/{username}/{datetime}")
    public ResponseBean updateOrderStatus(@PathVariable("orderid")String orderId,
                                          @PathVariable("username")String userName,
                                          @PathVariable("datetime")Date dateTime){
        try {
            int res = planService.updateOrderStatus(orderId,userName,dateTime);
            if(res == 2){
                return ResponseBean.success("更新订单状态成功",HttpServletResponse.SC_CREATED);
            }else if(res == 0){
                return ResponseBean.error("订单已完成，无须修改状态",HttpServletResponse.SC_NOT_FOUND);
            }else {
                return ResponseBean.error("订单已作废，无法修改状态",HttpServletResponse.SC_NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("更新订单状态错误",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

}
