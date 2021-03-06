package com.xtei.tailorsys.controller.order;

import com.xtei.tailorsys.entity.Order;
import com.xtei.tailorsys.entity.OrderFabricDetail;
import com.xtei.tailorsys.entity.VO.OrderFabricDetailVO;
import com.xtei.tailorsys.entity.response.ResponseBean;
import com.xtei.tailorsys.service.OrderService;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * FileName: OrderController
 * Author: Li Zihao
 * Date: 2021/3/27 9:54
 * Description: 订单管理页面视图控制器
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 获取订单列表
     */
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

        try {
            PageResult pageResult = orderService.findOrderViewList(orderid, customername, clothtypeid, paymentmethod, orderstatus, screenfield, date, ordertype, pagenum, pagesize);
            if (pageResult != null) {
                return ResponseBean.success("获取订单列表成功", HttpServletResponse.SC_OK, pageResult);
            } else {
                return ResponseBean.error("获取订单列表为空", HttpServletResponse.SC_NOT_FOUND, null);
            }
        } catch (Exception e) {
            return ResponseBean.error("获取订单列表错误", HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
        }

    }

    /**
     * 根据编号获取订单信息
     */
    @GetMapping("/{orderid}")
    public ResponseBean getOrderById(@PathVariable("orderid") String orderId) {
        try {
            Order order = orderService.findOrderById(orderId);
            if (order != null) {
                return ResponseBean.success("获取订单信息成功", HttpServletResponse.SC_PARTIAL_CONTENT, order);
            } else {
                return ResponseBean.error("获取订单信息为空", HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseBean.error("获取订单信息失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据订单编号获取订单详情信息
     */
    @GetMapping("/orderdetailfabric/{orderid}")
    public ResponseBean getOrderDetailAndFabric(@PathVariable("orderid") String orderId) {
        try {
            List<OrderFabricDetailVO> orderFabricDetailVOList = orderService.findOrderDetailAndFabricInfoList(orderId);
            if (orderFabricDetailVOList != null) {
                return ResponseBean.success("获取订单详细信息成功", HttpServletResponse.SC_PARTIAL_CONTENT, orderFabricDetailVOList);
            } else {
                return ResponseBean.error("获取订单详细信息失败", HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("获取订单详细信息失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }


    /**
     * 更新订单信息
     */
    @PutMapping("/{orderid}")
    public ResponseBean updateOrder(@PathVariable("orderid") String orderId, @RequestBody Order order) {
        Order orderV = order;
        orderV.setOrderId(orderId);
        try {
            orderService.updateOrder(orderV);
            return ResponseBean.success("更新订单信息成功", HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            return ResponseBean.error("更新订单信息失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 更新订单详情信息
     */
    @PutMapping("/orderdetail/{ofdno}")
    public ResponseBean updateOrderFabricDetail(@PathVariable("ofdno") Integer ofdNo, @RequestBody OrderFabricDetail orderFabricDetail) {
        OrderFabricDetail orderFabricDetailV = orderFabricDetail;
        orderFabricDetailV.setOfdNo(ofdNo);
        try {
            orderService.updateOrderFabricDetail(orderFabricDetailV);
            return ResponseBean.success("更新订单详情信息成功", HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("更新订单详细信息错误", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * 删除订单详情信息
     */
    @DeleteMapping("/orderdetail/{ofdno}")
    public ResponseBean deleteOrderFabricDetail(@PathVariable("ofdno") Integer ofdno) {

        try {
            if (orderService.deleteOrderFabricDetail(ofdno) == 2) {
                return ResponseBean.success("删除订单详情信息成功", HttpServletResponse.SC_CREATED);
            } else {
                return ResponseBean.error("删除订单详情信息失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("删除订单详情信息错误");
        }

    }

    /**
     * 更新订单状态
     */
    @PutMapping(value = "/orderstatus/{orderid}/{username}/{datetime}/{orderstatus}")
    public ResponseBean updateOrderStatus(@PathVariable("orderid") String orderId,
                                          @PathVariable("username") String userName,
                                          @PathVariable("datetime") Date dateTime,
                                          @PathVariable("orderstatus") Integer orderStatus) {
        try {
            orderService.updateOrderStatus(orderId, userName, dateTime, orderStatus);
            return ResponseBean.error("更新订单状态成功");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("更新订单状态错误");
        }

    }

    /**
     * 新增订单详情信息
     */
    @PostMapping("/orderdetail")
    public ResponseBean addOrderFabricDetail(@RequestBody OrderFabricDetail orderFabricDetail) {

        try {
            orderService.addOrderFabricDetail(orderFabricDetail);
            return ResponseBean.success("新增订单详情信息成功", HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("新增订单详情信息错误", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


}
