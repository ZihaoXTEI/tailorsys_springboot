package com.xtei.tailorsys.service;

import com.xtei.tailorsys.entity.ClothConsumption;
import com.xtei.tailorsys.entity.ClothType;
import com.xtei.tailorsys.entity.FabricType;
import com.xtei.tailorsys.entity.VO.ClothConsumptionVO;
import com.xtei.tailorsys.util.pagehelper.PageResult;

/**
 * FileName: DataService
 * Author: Li Zihao
 * Date: 2021/2/25 12:31
 * Description:
 */
public interface DataService {

    int addClothType(ClothType clothType);

    ClothType findClothTypeById(Integer clothtypeId);

    PageResult findClothTypeList(String query, Integer pageNum, Integer pageSize);

    int updateClothType(ClothType clothType);


    int addFabricType(FabricType fabricType);

    FabricType findFabricTypeById(Integer fabrictypeId);

    PageResult findFabricTypeList(String fabrictypeName,String fabrictypeCategory, Integer pageNum, Integer pageSize);

    int updateFabricType(FabricType fabricType);


    ClothConsumption findClothConsumptionById(Integer consumId);

    ClothConsumptionVO findClothConsumptionVOById(Integer consumId);

    ClothConsumption findClothConsumptionByClothtypeIdAndConsumWidth(Integer clothtypeId, Double consumWidth);

    PageResult findClothConsumptionList(String query, Integer pageNum, Integer pageSize);

    int updateClothConsumption(ClothConsumption clothConsumption);

    int addClothConsumption(ClothConsumption clothConsumption);




}
