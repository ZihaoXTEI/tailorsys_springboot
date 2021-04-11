package com.xtei.tailorsys.service;

import com.xtei.tailorsys.entity.FabricInfo;

import java.util.List;
import java.util.Map;

/**
 * FileName: FabricInfoService
 * Author: Li Zihao
 * Date: 2021/3/9 15:03
 * Description:
 */
public interface FabricInfoService {

    int deleteFabricInfo(Integer fabricId);

    int addFabricInfo(FabricInfo fabricInfo);

    FabricInfo findFabricInfoById(Integer fabricId);

    int updateFabricInfo(FabricInfo fabricInfo);

    List<Map<Integer,String>> findByName(String name);

    int getNumberOfFabricInfo();
}
