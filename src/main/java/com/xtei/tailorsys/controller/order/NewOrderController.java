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
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @Resource
    private OrderService orderService;

    @Resource
    private DataService dataService;

    @Resource
    private FabricStockService fabricStockService;

    @Resource
    private AnthropometryService anthropometryService;


    /**
     * 新增订单
     */
    @PostMapping(value = "/")
    public ResponseBean newOrder(@RequestBody OrderVO orderVO){
        String orderId = FormatUtils.generatedOrderId();
        Order order = OrderConverter.converterToOrder(orderVO);
        order.setOrderId(orderId);

        List<OrderFabricDetail> orderFabricDetails = orderVO.getOrderFabricDetailList();

        Date date = new Date();          // 获取一个Date对象
        Timestamp timeStamp = new Timestamp(date.getTime());     //   讲日期时间转换为数据库中的timestamp类型
        order.setCreatedTime(timeStamp);

        System.out.println(order);
        System.out.println(orderFabricDetails);


        try {
            orderService.submitOrder(order,orderFabricDetails);
        }catch (Exception e){
            //手动回滚事件
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseBean.error("提交订单错误");
        }
        return ResponseBean.success("提交订单成功");

    }

    /**
     * 根据编号获取顾客量体信息
     */
    @GetMapping(value = "/{anthrid}")
    public ResponseBean getAnthropometryById(@PathVariable("anthrid") Integer anthrId) {
        Anthropometry anthropometry = anthropometryService.findAnthropometryById(anthrId);
        if (anthropometry != null) {
            return ResponseBean.success("获取顾客量体信息成功", anthropometry);
        } else {
            return ResponseBean.error("获取顾客量体信息失败");
        }
    }

    /**
     * 新建顾客的量体数据
     *
     * @param customerId
     * @param anthrNote
     * @return
     */
    @PostMapping(value = "newanthr/{cusid}/{anthrnote}")
    public ResponseBean addAnthropometry(@PathVariable("cusid") Integer customerId, @PathVariable("anthrnote") String anthrNote) {
        System.out.println(customerId);
        System.out.println(anthrNote);
        Date currentTime = new Date();

        int res = anthropometryService.addAnthropometry(customerId, anthrNote, currentTime);
        System.out.println(res);
        if (res != 0) {
            return ResponseBean.success("新建顾客量体信息成功", res);
        }

        System.out.println("错误");
        return ResponseBean.error("新建顾客量体信息错误");

        //return ResponseBean.error("新建顾客量体信息失败");


    }

    /**
     * 更新顾客量体信息
     */
    @PutMapping("newanthr/{anthrid}")
    public ResponseBean updateAnthropometry(@PathVariable("anthrid") Integer anthrId, @RequestBody Anthropometry anthropometry) {
        Anthropometry anthropometryV = anthropometry;
        anthropometryV.setAnthrId(anthrId);
        Date currentTime = new Date();
        anthropometryV.setMeasureTime(currentTime);
        if (anthropometryService.updateAnthropometry(anthropometryV) == 1) {
            return ResponseBean.success("保存顾客量体信息成功");
        } else {
            return ResponseBean.error("保持顾客量体信息失败，请确认输入数据是否合法");
        }
    }

    /**
     * 计算布料库存量是否满足订单需求
     */
    @GetMapping("verif/{anthtrId}/{clothtypeid}/{fabrics}")
    public ResponseBean x(@PathVariable("anthtrId") Integer anthrId, @PathVariable("clothtypeid") Integer clothtypeId, @PathVariable("fabrics") Integer[] fabrics) {
        if (anthrId == null || anthrId == 0) {
            return ResponseBean.error("验证数据异常");
        } else if (clothtypeId == null || clothtypeId == 0) {
            return ResponseBean.error("验证数据异常");
        } else if (fabrics == null || fabrics.length == 0) {
            return ResponseBean.error("验证数据异常");
        }

        System.out.println(fabrics);

        double requirement = 0.0;
        ArrayList<String> messageList = new ArrayList<>();
        Anthropometry anthropometry = anthropometryService.findAnthropometryById(anthrId);
        ClothConsumption clothConsumption = dataService.findClothConsumptionById(clothtypeId);


        try {
            requirement = CalculationUntils.calculaClothConsumption(anthropometry, clothConsumption);
            System.out.println("RES" +requirement);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("验证数据异常,算法错误");
        }
        messageList.add(String.valueOf(requirement));

        for (Integer fabricId : fabrics) {
            double v = fabricStockService.getReallyFasStock(fabricId);
            if (v < requirement) {
                messageList.add(String.valueOf(fabricId));
            }
        }

        if (messageList.size() == 1) {
            return ResponseBean.success("所需布料库存充足",messageList);
        } else {
            return ResponseBean.error("所需布料存在不足", messageList);
        }
    }


}
