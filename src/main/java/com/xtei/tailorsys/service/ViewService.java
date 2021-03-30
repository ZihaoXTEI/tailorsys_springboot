package com.xtei.tailorsys.service;

import com.xtei.tailorsys.model.Permission;
import com.xtei.tailorsys.model.VO.FabricTypeVO;

import java.util.List;
import java.util.Map;

/**
 * FileName: ViewService
 * Author: Li Zihao
 * Date: 2021/2/10 16:41
 * Description:
 */

public interface ViewService {

    List<Permission> findPermissionList();

    List<Map<Integer,String>> findFabricInfoMap(String query);

    List<FabricTypeVO> findFabricTypeMap();
    //List<Map<Integer,String>> findFabricTypeMap();

    List<Map<Integer,String>> findSupplierMap();

    List<Map<Integer,String>> findUserMap();

    List<Map<Integer,String>> findClothTypeMap();

    List<Map<Integer,String>> findCustomerMap();

    List<Map<Integer,String>> findAnthropometryMap(Integer customerId);

    List<Map<String,String>> findClothtypeColumnInfo();

    List<Map<String,String>> findClothconsumptionColumnInfo();

    List<Map<String,String>> findAnthropometricColumnInfo();

}
