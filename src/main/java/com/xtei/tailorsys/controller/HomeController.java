package com.xtei.tailorsys.controller;

import com.xtei.tailorsys.model.response.ResponseBean;
import com.xtei.tailorsys.service.CustomerService;
import com.xtei.tailorsys.service.FabricInfoService;
import com.xtei.tailorsys.service.FabricStockService;
import com.xtei.tailorsys.service.OrderService;
import com.xtei.tailorsys.util.HttpStatus;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * FileName: HomeController
 * Author: Li Zihao
 * Date: 2021/3/30 14:32
 * Description:
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @Resource
    private CustomerService customerService;
    @Resource
    private FabricInfoService fabricInfoService;
    @Resource
    private OrderService orderService;
    @Resource
    private FabricStockService fabricStockService;

    /**
     * 获取顾客总人数`BULD
     */
    @GetMapping("/infobox")
    public ResponseBean getInfoBox(){
        ArrayList<Integer> dataList = new ArrayList<>();
        try{
            dataList.add(customerService.getNumberOfCustomer());
            dataList.add(fabricInfoService.getNumberOfFabricInfo());
            dataList.add(orderService.getNumberOfIncompleteOrder());
            dataList.add(orderService.getNumberOfOrder());
            return ResponseBean.success("获取数据成功", HttpStatus.GET_VIEW_DATA,dataList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("获取数据错误",HttpStatus.SERIOUS_ERROR);
        }
    }

    /**
     * 订单信息（低于10天）
     */
    @GetMapping("/orderinfo")
    public ResponseBean getIncompleteOrderInfo(){
        List<Map> dataList = orderService.getInfoOfIncompleteOrder();
        if(dataList != null){
            return ResponseBean.success("获取数据成功",HttpStatus.GET_VIEW_DATA,dataList);
        }else {
            return ResponseBean.error("获取数据失败",HttpStatus.SERIOUS_ERROR);
        }
    }

    /**
     * 库存信息(低于6米（600厘米））
     */
    @GetMapping("/fabricstock")
    public ResponseBean getFabricStock(){
        List<Map> dataList = fabricStockService.getFabricInfoLower();
        if(dataList != null){
            return ResponseBean.success("获取数据成功",HttpStatus.GET_VIEW_DATA,dataList);
        }else {
            return ResponseBean.error("获取数据失败",HttpStatus.SERIOUS_ERROR);
        }
    }

    /**
     * 顾客比例
     */
    @GetMapping("/customersexratio")
    public ResponseBean getCustomerSexRatio(){
        List<Map> dataList = customerService.getCustomerSexRatio();
        if(dataList != null){
            return ResponseBean.success("获取数据成功",HttpStatus.GET_VIEW_DATA,dataList);
        }else {
            return ResponseBean.error("获取数据失败",HttpStatus.SERIOUS_ERROR);
        }
    }

    /**
     * 单周营业额
     */
}
