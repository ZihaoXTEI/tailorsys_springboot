package com.xtei.tailorsys.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xtei.tailorsys.mapper.SupplierMapper;
import com.xtei.tailorsys.entity.Supplier;
import com.xtei.tailorsys.service.SupplierService;
import com.xtei.tailorsys.util.PageHelperUtils;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * FileName: SupplierServiceImpl
 * Author: Li Zihao
 * Date: 2021/3/8 20:38
 * Description:
 */
@Service("supplierService")
@Transactional(rollbackFor = Exception.class)
public class SupplierServiceImpl implements SupplierService {

    @Resource
    private SupplierMapper supplierMapper;

    @Override
    public int deleteSupplier(Integer supId) {
        int res = supplierMapper.deleteByPrimaryKey(supId);
        return res;
    }

    @Override
    public int addSupplier(Supplier supplier) {
        int res = supplierMapper.insert(supplier);
        return res;
    }

    @Override
    public Supplier findSupplierById(Integer supId) {
        Supplier supplier = supplierMapper.selectByPrimaryKey(supId);
        return supplier;
    }

    @Override
    public PageResult findSupplierList(String query, Integer pageNum, Integer pageSize) {
        return PageHelperUtils.getPageResult(getPageInfo(query,pageNum,pageSize));
    }

    private PageInfo<Supplier> getPageInfo(String query, Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Supplier> supplierList =supplierMapper.selectAll(query);
        return new PageInfo<Supplier>(supplierList);
    }

    @Override
    public int updateSupplierInfo(Supplier supplier) {
        int res = supplierMapper.updateByPrimaryKey(supplier);
        return res;
    }

    @Override
    public int updateSupplierStatus(Integer supId,boolean status){
        int res = supplierMapper.updateSupplierStatus(supId,status);
        return res;
    }

    @Override
    public List<Supplier> getAllSupplier(){
        List<Supplier> supplierList = supplierMapper.selectAll(null);
        return supplierList;
    }
}
