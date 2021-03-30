package com.xtei.tailorsys.controller.order;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.xtei.tailorsys.model.Order;
import com.xtei.tailorsys.model.OrderFabricDetail;
import com.xtei.tailorsys.model.VO.OrderFabricDetailVO;
import com.xtei.tailorsys.model.response.ResponseBean;
import com.xtei.tailorsys.service.OrderService;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * FileName: OrderController
 * Author: Li Zihao
 * Date: 2021/3/27 9:54
 * Description:
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/")
    public ResponseBean<PageResult> getOrderViewList(@RequestParam(value = "orderid", defaultValue = "") String orderid,
                                                    @RequestParam(value = "customername", defaultValue = "") String customername,
                                                    @RequestParam(value = "clothtypeid", defaultValue = "") Integer clothtypeid,
                                                    @RequestParam(value = "paymentmethod", defaultValue = "") Integer paymentmethod,
                                                    @RequestParam(value = "orderstatus", defaultValue = "") Integer orderstatus,
                                                    @RequestParam(value = "screenfield", defaultValue = "") String screenfield,
                                                    @RequestParam(value = "date", defaultValue = "") Date[] date,
                                                    @RequestParam(value = "ordertype", defaultValue = "ASC") String ordertype,
                                                    @RequestParam(value = "pagenum", defaultValue = "1") Integer pagenum,
                                                    @RequestParam(value = "pagesize", defaultValue = "10") Integer pagesize) {

        PageResult pageResult = orderService.findOrderViewList(orderid,customername,clothtypeid,paymentmethod,orderstatus,screenfield,date,ordertype,pagenum, pagesize);
        if (pageResult != null) {
            return ResponseBean.success("获取订单列表成功", pageResult);
        } else {
            return ResponseBean.error("获取订单列表失败", null);
        }
    }

    @GetMapping("/{orderid}")
    public ResponseBean getOrderById(@PathVariable("orderid")String orderId){
        Order order = orderService.findOrderById(orderId);
        if(order != null){
            return ResponseBean.success("获取订单信息成功",order);
        }else {
            return ResponseBean.error("获取订单信息失败");
        }
    }

    @GetMapping("/orderdetailfabric/{orderid}")
    public ResponseBean getOrderDetailAndFabric(@PathVariable("orderid")String orderId){
        List<OrderFabricDetailVO> orderFabricDetailVOList = orderService.findOrderDetailAndFabricInfoList(orderId);
        if(orderFabricDetailVOList != null){
            return ResponseBean.success("获取订单详细信息成功",orderFabricDetailVOList);
        }else {
            return ResponseBean.error("获取订单详细信息失败");
        }
    }


    /**
     * 更新订单信息
     */
    @PutMapping("/{orderid}")
    public ResponseBean updateOrder(@PathVariable("orderid")String orderId, @RequestBody Order order){
        Order orderV = order;
        orderV.setOrderId(orderId);
        if(orderService.updateOrder(orderV) == 1){
            return ResponseBean.success("更新订单信息成功");
        }else {
            return ResponseBean.error("更新订单信息失败");
        }
    }

    /**
     * 更新订单详情信息
     */
    @PutMapping("/orderdetail/{ofdno}")
    public ResponseBean updateOrderFabricDetail(@PathVariable("ofdno")Integer ofdNo,@RequestBody OrderFabricDetail orderFabricDetail){
        OrderFabricDetail orderFabricDetailV = orderFabricDetail;
        orderFabricDetailV.setOfdNo(ofdNo);
        try {
            if(orderService.updateOrderFabricDetail(orderFabricDetailV) == 2){
                return ResponseBean.success("更新订单详情信息成功");
            }else {
                return ResponseBean.error("更新订单详细信息失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("更新订单详细信息错误");
        }

    }

    /**
     * 删除订单详情信息
     */
    @DeleteMapping("/orderdetail/{ofdno}")
    public ResponseBean deleteOrderFabricDetail(@PathVariable("ofdno")Integer ofdno){

        try {
            if( orderService.deleteOrderFabricDetail(ofdno) == 2){
                return ResponseBean.success("删除订单详情信息成功");
            }else {
                return ResponseBean.error("删除订单详情信息失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("删除订单详情信息错误");
        }

    }

    /**
     * 新增订单详情信息
     */
    @PostMapping("/orderdetail")
    public ResponseBean addOrderFabricDetail(@RequestBody OrderFabricDetail orderFabricDetail){

        try {
            if(orderService.addOrderFabricDetail(orderFabricDetail) == 2){
                return ResponseBean.success("新增订单详情信息成功");
            }else {
                return ResponseBean.error("新增订单详情信息失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("新增订单详情信息错误");
        }
    }



}
