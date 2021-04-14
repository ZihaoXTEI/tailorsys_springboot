package com.xtei.tailorsys.controller;

import com.xtei.tailorsys.entity.response.ResponseBean;
import com.xtei.tailorsys.service.CustomerService;
import com.xtei.tailorsys.service.FabricInfoService;
import com.xtei.tailorsys.service.FabricStockService;
import com.xtei.tailorsys.service.OrderService;
import com.xtei.tailorsys.util.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * FileName: HomeController
 * Author: Li Zihao
 * Date: 2021/3/30 14:32
 * Description: 前端布局视图控制器
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private FabricInfoService fabricInfoService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private FabricStockService fabricStockService;

    /**
     * 获取顾客总人数
     */
    @GetMapping("/infobox")
    public ResponseBean getInfoBox(){
        ArrayList<Integer> dataList = new ArrayList<>();
        try{
            dataList.add(customerService.getNumberOfCustomer());
            dataList.add(fabricInfoService.getNumberOfFabricInfo());
            dataList.add(orderService.getNumberOfIncompleteOrder());
            dataList.add(orderService.getNumberOfOrder());
            return ResponseBean.success("获取顾客总人数成功", HttpServletResponse.SC_PARTIAL_CONTENT,dataList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("获取顾客总人数错误",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 订单信息（低于10天）
     */
    @GetMapping("/orderinfo")
    public ResponseBean getIncompleteOrderInfo(){

        try {
            List<Map> dataList = orderService.getInfoOfIncompleteOrder();
            return ResponseBean.success("获取订单数据成功",HttpServletResponse.SC_PARTIAL_CONTENT,dataList);
        }catch (Exception e){
            return ResponseBean.error("获取订单数据失败",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 库存信息(低于6米（600厘米））
     */
    @GetMapping("/fabricstock")
    public ResponseBean getFabricStock(){
        try {
            List<Map> dataList = fabricStockService.getFabricInfoLower();
            return ResponseBean.success("获取库存数据成功",HttpServletResponse.SC_PARTIAL_CONTENT,dataList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("获取库存数据失败",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 顾客比例
     */
    @GetMapping("/customersexratio")
    public ResponseBean getCustomerSexRatio(){
        try {
            List<Map> dataList = customerService.getCustomerSexRatio();
            if(dataList != null){
                return ResponseBean.success("获取顾客数据成功",HttpServletResponse.SC_PARTIAL_CONTENT,dataList);
            }else {
                return ResponseBean.error("获取顾客数据为空",HttpServletResponse.SC_NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("获取顾客数据失败",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }


    }

}
