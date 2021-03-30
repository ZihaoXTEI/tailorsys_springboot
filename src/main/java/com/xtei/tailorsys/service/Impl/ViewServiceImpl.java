package com.xtei.tailorsys.service.Impl;

import com.xtei.tailorsys.mapper.*;
import com.xtei.tailorsys.model.Permission;
import com.xtei.tailorsys.model.VO.FabricTypeVO;
import com.xtei.tailorsys.service.ViewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * FileName: ViewService
 * Author: Li Zihao
 * Date: 2021/3/10 17:06
 * Description:
 */

@Service("viewService")
@Transactional(rollbackFor = Exception.class)
public class ViewServiceImpl implements ViewService {

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private FabricInfoMapper fabricInfoMapper;

    @Resource
    private FabricTypeMapper fabricTypeMapper;

    @Resource
    private SupplierMapper supplierMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ClothTypeMapper clothTypeMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private AnthropometryMapper anthropometryMapper;

    @Resource
    private ClothConsumptionMapper clothConsumptionMapper;

    @Override
    public List<Permission> findPermissionList(){
        List<Permission> permissionList = permissionMapper.getMenu();
        return permissionList;
    }

    @Override
    public List<Map<Integer,String>> findFabricInfoMap(String query){
        List<Map<Integer,String>> fabricInfoMap = fabricInfoMapper.selectFabricInfoMap(query);
        return fabricInfoMap;
    }

    @Override
/*    public List<Map<Integer, String>> findFabricTypeMap(){
        List<Map<Integer, String>> fabricTypeMap = fabricTypeMapper.selectFabricTypeMap();
        return fabricTypeMap;
    }*/
    public List<FabricTypeVO> findFabricTypeMap(){
        List<FabricTypeVO> fabricTypeMap = fabricTypeMapper.selectFabricTypeMap();
        return fabricTypeMap;
    }

    @Override
    public List<Map<Integer,String>> findSupplierMap(){
        List<Map<Integer,String>> supplierMap = supplierMapper.selectSupplierMap();
        return supplierMap;
    }

    @Override
    public List<Map<Integer,String>> findUserMap(){
        List<Map<Integer,String>> userMap = userMapper.selectUserMap();
        return userMap;
    }

    @Override
    public List<Map<Integer,String>> findClothTypeMap(){
        List<Map<Integer,String>> clothTypeMap = clothTypeMapper.selectClothTypeMap();
        return clothTypeMap;
    }

    @Override
    public List<Map<Integer, String>> findCustomerMap() {
        List<Map<Integer,String>> customerMap = customerMapper.selectCustomerMap();
        return customerMap;
    }

    @Override
    public List<Map<Integer, String>> findAnthropometryMap(Integer customerId) {
        List<Map<Integer,String>> anthropometryMap = anthropometryMapper.selectAnthropometryMap(customerId);
        return anthropometryMap;
    }

    @Override
    public List<Map<String,String>> findClothtypeColumnInfo(){
        List<Map<String,String>> clothtypecolumnInfoList = clothTypeMapper.selectColumnInfo();
        return clothtypecolumnInfoList;
    }

    @Override
    public List<Map<String,String>> findClothconsumptionColumnInfo(){
        List<Map<String,String>> clothconsumptionColumnInfoList = clothConsumptionMapper.selectColumnInfo();
        return clothconsumptionColumnInfoList;
    }

    @Override
    public List<Map<String, String>> findAnthropometricColumnInfo() {
        List<Map<String,String>> anthropometricColumnInfoList = anthropometryMapper.selectColumnInfo();
        return anthropometricColumnInfoList;
    }
}
