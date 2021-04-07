package com.xtei.tailorsys.controller.order;

import com.xtei.tailorsys.converter.OrderConverter;
import com.xtei.tailorsys.model.Anthropometry;
import com.xtei.tailorsys.model.ClothConsumption;
import com.xtei.tailorsys.model.Order;
import com.xtei.tailorsys.model.OrderFabricDetail;
import com.xtei.tailorsys.model.VO.OrderVO;
import com.xtei.tailorsys.model.response.ResponseBean;
import com.xtei.tailorsys.service.AnthropometryService;
import com.xtei.tailorsys.service.DataService;
import com.xtei.tailorsys.service.FabricStockService;
import com.xtei.tailorsys.service.OrderService;
import com.xtei.tailorsys.util.CalculationUntils;
import com.xtei.tailorsys.util.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * FileName: NewOrderController
 * Author: Li Zihao
 * Date: 2021/3/13 17:42
 * Description:
 */
@RestController
@RequestMapping("neworder")
public class NewOrderController {

    @Autowired
    private OrderService orderService;


    /**
     * 新增订单
     */
    @PostMapping(value = "/")
    public ResponseBean newOrder(@RequestBody OrderVO orderVO){
        String orderId = FormatUtils.generatedOrderId();
        Order order = OrderConverter.converterToOrder(orderVO);
        order.setOrderId(orderId);

        List<OrderFabricDetail> orderFabricDetails = orderVO.getOrderFabricDetailList();

        // 设置订单创建时间
        Date date = new Date();                                 // 获取一个Date对象
        Timestamp timeStamp = new Timestamp(date.getTime());    // 将日期时间转换为数据库中的timestamp类型
        order.setCreatedTime(timeStamp);

        try {
            orderService.submitOrder(order,orderFabricDetails);
        }catch (Exception e){
            // 手动回滚事件
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseBean.error("提交订单错误", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return ResponseBean.success("提交订单成功", HttpServletResponse.SC_CREATED);

    }

}
