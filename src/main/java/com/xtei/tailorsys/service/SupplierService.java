package com.xtei.tailorsys.service;

import com.xtei.tailorsys.entity.Supplier;
import com.xtei.tailorsys.util.pagehelper.PageResult;

import java.util.List;

/**
 * FileName: SupplierService
 * Author: Li Zihao
 * Date: 2021/2/20 20:06
 * Description:
 */
public interface SupplierService {

    int deleteSupplier(Integer supId);

    int addSupplier(Supplier supplier);

    Supplier findSupplierById(Integer supId);

    PageResult findSupplierList(String query, Integer pageNum, Integer pageSize);

    int updateSupplierInfo(Supplier supplier);

    int updateSupplierStatus(Integer supId,boolean supStatus);

    List<Supplier> getAllSupplier();




}
