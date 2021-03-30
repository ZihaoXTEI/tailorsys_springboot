package com.xtei.tailorsys.service;

import com.xtei.tailorsys.model.ClothType;
import com.xtei.tailorsys.util.pagehelper.PageResult;

/**
 * FileName: ClothTypeService
 * Author: Li Zihao
 * Date: 2021/3/12 11:59
 * Description:
 */
public interface ClothTypeService {

    int addClothType();

    ClothType findClothTypeById(Integer clothtypeId);

    PageResult findClothTypeList(String query,Integer pageNum,Integer pageSize);

    int updateClothType(ClothType clothType);

}
