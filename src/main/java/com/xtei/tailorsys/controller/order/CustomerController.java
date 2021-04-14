package com.xtei.tailorsys.controller.order;

import com.xtei.tailorsys.entity.Customer;
import com.xtei.tailorsys.entity.response.ResponseBean;
import com.xtei.tailorsys.service.CustomerService;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * FileName: CustomerController
 * Author: Li Zihao
 * Date: 2021/2/20 23:03
 * Description: 顾客信息管理视图控制器
 */

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /*
     *新增顾客信息
     */
    @PostMapping(value = "/")
    public ResponseBean addCustomer(@RequestBody Customer customer) {
        try{
            customerService.addCustomer(customer);
            return ResponseBean.success("新增顾客信息成功", HttpServletResponse.SC_CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("新增顾客信息失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /*
     *修改顾客信息
     */
    @PutMapping(value = "/{cusid}")
    public ResponseBean updateCustomer(@PathVariable("cusid") Integer cusId, @RequestBody Customer customer) {
        Customer customerV = customer;
        customerV.setCustomerId(cusId);
        try{
            customerService.updateCustomerInfo(customerV);
            return ResponseBean.success("修改顾客信息成功", HttpServletResponse.SC_CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("修改顾客信息失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /*
     *根据编号获取顾客信息
     */
    @GetMapping(value = "/{cusid}")
    public ResponseBean getCustomerById(@PathVariable("cusid") Integer cusId) {
        try {
            Customer customer = customerService.findCustomerById(cusId);
            if(customer != null){
                return ResponseBean.success("获取顾客信息成功", HttpServletResponse.SC_OK, customer);
            }else {
                return ResponseBean.error("获取顾客信息失败", HttpServletResponse.SC_NOT_FOUND);
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("获取顾客信息错误", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /*
     *获取顾客数据列表
     */
    @GetMapping("/")
    public ResponseBean<PageResult> getCustomerList(@RequestParam(value = "customername", defaultValue = "") String customername,
                                                    @RequestParam(value = "customersex", defaultValue = "") String customersex,
                                                    @RequestParam(value = "pagenum", defaultValue = "1") Integer pagenum,
                                                    @RequestParam(value = "pagesize", defaultValue = "10") Integer pagesize) {
        try {
            PageResult pageResult = customerService.findCustomerList(customername, customersex, pagenum, pagesize);
            if (pageResult != null) {
                return ResponseBean.success("获取顾客列表成功", HttpServletResponse.SC_OK, pageResult);
            } else {
                return ResponseBean.error("获取顾客列表失败", HttpServletResponse.SC_NOT_FOUND, null);
            }
        }catch (Exception e){
            return ResponseBean.error("获取顾客列表错误", HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
        }

    }
}
