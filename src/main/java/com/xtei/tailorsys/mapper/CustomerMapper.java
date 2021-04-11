package com.xtei.tailorsys.mapper;

import com.xtei.tailorsys.entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CustomerMapper {

    int deleteByPrimaryKey(Integer customerId);

    int insert(Customer record);

    Customer selectByPrimaryKey(Integer customerId);

    List<Customer> selectAll(@Param("customername") String customername,@Param("customersex") String customersex);

    int updateByPrimaryKey(Customer record);

    List<Map<Integer,String>> selectCustomerMap();

    int getNumberOfCustomer();

    List<Map> getCustomerSexRatio();
}