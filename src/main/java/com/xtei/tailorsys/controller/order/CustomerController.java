package com.xtei.tailorsys.controller.order;

import com.xtei.tailorsys.model.Customer;
import com.xtei.tailorsys.model.response.ResponseBean;
import com.xtei.tailorsys.service.CustomerService;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * FileName: CustomerController
 * Author: Li Zihao
 * Date: 2021/2/20 23:03
 * Description: 顾客信息管理模块
 */

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    /*
     *新增顾客信息
     */
    @PostMapping(value = "/")
    public ResponseBean addCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
        if (customer.getCustomerName() == null || customer.getCustomerName().trim() == "") {
            return ResponseBean.error("顾客名称不能为空");
        }

        if (customerService.addCustomer(customer) == 1) {
            return ResponseBean.success("新增顾客信息成功");
        } else {
            return ResponseBean.error("新增顾客信息失败");
        }
    }

    /*
     *修改顾客信息
     */
    @PutMapping(value = "/{cusid}")
    public ResponseBean updateCustomer(@PathVariable("cusid") Integer cusId, @RequestBody Customer customer) {
        Customer customerV = customer;
        customerV.setCustomerId(cusId);
        if (customerService.updateCustomerInfo(customerV) == 1) {
            return ResponseBean.success("修改顾客信息成功");
        } else {
            return ResponseBean.error("修改顾客信息失败");
        }
    }

    /*
     *根据编号获取顾客信息
     */
    @GetMapping(value = "/{cusid}")
    public ResponseBean getCustomerById(@PathVariable("cusid") Integer cusId) {
        Customer customer = customerService.findCustomerById(cusId);
        if (customer != null) {
            return ResponseBean.success("获取顾客信息成功", customer);
        } else {
            return ResponseBean.error("获取顾客信息失败");
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
        PageResult pageResult = customerService.findCustomerList(customername, customersex, pagenum, pagesize);
        if (pageResult != null) {
            return ResponseBean.success("获取顾客列表成功", pageResult);
        } else {
            return ResponseBean.error("获取顾客列表失败", null);
        }
    }

    /*
     *依据输入顾客名称搜索符合的顾客信息
     */
}
