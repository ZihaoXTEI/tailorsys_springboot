package com.xtei.tailorsys.service;

import com.xtei.tailorsys.entity.Customer;
import com.xtei.tailorsys.util.pagehelper.PageResult;

import java.util.List;
import java.util.Map;

/**
 * FileName: CustomerService
 * Author: Li Zihao
 * Date: 2021/3/8 23:05
 * Description:
 */
public interface CustomerService {

    int deleteCustomer(Integer customerId);

    int addCustomer(Customer customer);

    Customer findCustomerById(Integer customerId);

    PageResult findCustomerList(String customerName,String customerSex, Integer pageNum, Integer pageSize);

    int updateCustomerInfo(Customer customer);

    int getNumberOfCustomer();

    List<Map> getCustomerSexRatio();

    List<Customer> getAllCustomer();

}
