package com.xtei.tailorsys.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xtei.tailorsys.mapper.ClothTypeMapper;
import com.xtei.tailorsys.model.ClothType;
import com.xtei.tailorsys.service.ClothTypeService;
import com.xtei.tailorsys.util.PageHelperUtils;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * FileName: ClothTypeServiceImpl
 * Author: Li Zihao
 * Date: 2021/2/25 12:06
 * Description:
 */

@Service("clothTypeService")
@Transactional(rollbackFor = Exception.class)
public class ClothTypeServiceImpl implements ClothTypeService {

    @Resource
    private ClothTypeMapper clothTypeMapper;

    @Override
    public int addClothType() {
        //int res = clothTypeMapper.insert();
        return 0;
    }

    @Override
    public ClothType findClothTypeById(Integer clothtypeId) {
        ClothType clothType = clothTypeMapper.selectByPrimaryKey(clothtypeId);
        return clothType;
    }

    @Override
    public PageResult findClothTypeList(String query, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ClothType> clothTypeList = clothTypeMapper.selectAll(query);
        PageInfo<ClothType> clothTypePageInfo = new PageInfo<ClothType>(clothTypeList);
        return PageHelperUtils.getPageResult(clothTypePageInfo);
    }

    @Override
    public int updateClothType(ClothType clothType) {
        int res = clothTypeMapper.updateByPrimaryKey(clothType);
        return res;
    }
}
