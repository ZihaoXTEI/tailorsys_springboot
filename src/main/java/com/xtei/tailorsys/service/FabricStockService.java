package com.xtei.tailorsys.service;

import com.xtei.tailorsys.entity.*;
import com.xtei.tailorsys.entity.VO.FabricReceiveInfoVO;
import com.xtei.tailorsys.entity.VO.FabricStockInfoVO;
import com.xtei.tailorsys.util.pagehelper.PageResult;

import java.util.List;
import java.util.Map;

/**
 * FileName: FabricReceiveInfoService
 * Author: Li Zihao
 * Date: 2021/3/9 16:20
 * Description:
 */
public interface FabricStockService {

    //int deleteFabricReceiveInfo(Integer farId);

    int addFabricReceiveInfo(FabricReceiveInfo fabricReceiveInfo);

    int updateFabricReceiveInfo(FabricReceiveInfo fabricReceiveInfo);

    PageResult findFabricReceiveInfoList(String query, Integer pageNum, Integer pageSize);

    int updateFabricStockInfo(FabricStockInfo FabricStockInfo, FabricInfo fabricInfo);

    PageResult findFabricStockInfoVOList(String fabricname, Integer fabrictype, String ordertype, String orderfield, Double min, Double max, Integer pagenum, Integer pagesize);

    FabricStockInfoVO findFabricStockInfoVOByFasId(Integer fasId);




    int addFabricUsedInfo(FabricUsedInfo fabricUsedInfo);

    int deleteFabricUsedInfo(Integer fauId);

    int updateFabricUsedInfo(FabricUsedInfo fabricUsedInfo);


    FabricReceiveInfo findFabricReceiveInfoById(Integer farId);

    double getFasStockByFabricId(Integer fabricId);
    double getReallyFasStock(Integer fabricId);

    List<Map> getFabricInfoLower();

    List<FabricStockInfoVO> getAllFabricStock();

    List<FabricReceiveInfoVO> getAllFabricReceive();



}
