package com.xtei.tailorsys.service.Impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xtei.tailorsys.mapper.CustomerMapper;
import com.xtei.tailorsys.model.Customer;
import com.xtei.tailorsys.service.CustomerService;
import com.xtei.tailorsys.util.PageHelperUtils;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * FileName: CustomerServiceImpl
 * Author: Li Zihao
 * Date: 2021/2/20 23:10
 * Description:
 */

@Service("customerService")
@Transactional(rollbackFor = Exception.class)
public class CustomerServiceImpl implements CustomerService {

    @Resource
    CustomerMapper customerMapper;

    @Override
    public int deleteCustomer(Integer customerId) {
        int res = customerMapper.deleteByPrimaryKey(customerId);
        return res;
    }

    @Override
    public int addCustomer(Customer customer) {
        int res = customerMapper.insert(customer);
        return res;
    }

    @Override
    public Customer findCustomerById(Integer customerId) {
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        return customer;
    }

    @Override
    public PageResult findCustomerList(String customerName,String customerSex, Integer pageNum, Integer pageSize) {
        return PageHelperUtils.getPageResult(getPageInfo(customerName,customerSex, pageNum, pageSize));
    }

    private PageInfo<Customer> getPageInfo(String customerName,String customerSex, Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Customer> customerList = customerMapper.selectAll(customerName,customerSex);
        return new PageInfo<Customer>(customerList);
    }

    @Override
    public int updateCustomerInfo(Customer customer) {
        int res = customerMapper.updateByPrimaryKey(customer);
        return res;
    }

    @Override
    public int getNumberOfCustomer(){
        int res = customerMapper.getNumberOfCustomer();
        return res;
    }

    @Override
    public List<Map> getCustomerSexRatio(){
        List<Map> dataList = customerMapper.getCustomerSexRatio();
        return dataList;
    }

    @Override
    public List<Customer> getAllCustomer(){
        List<Customer> customerList = customerMapper.selectAll(null,null);
        return customerList;
    }
}
