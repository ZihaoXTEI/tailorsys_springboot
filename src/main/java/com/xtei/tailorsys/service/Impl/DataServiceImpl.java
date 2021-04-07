package com.xtei.tailorsys.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xtei.tailorsys.mapper.ClothConsumptionMapper;
import com.xtei.tailorsys.mapper.ClothTypeMapper;
import com.xtei.tailorsys.mapper.FabricTypeMapper;
import com.xtei.tailorsys.mapper.VO.ClothConsumptionVOMapper;
import com.xtei.tailorsys.model.ClothConsumption;
import com.xtei.tailorsys.model.ClothType;
import com.xtei.tailorsys.model.FabricType;
import com.xtei.tailorsys.model.VO.ClothConsumptionVO;
import com.xtei.tailorsys.service.DataService;
import com.xtei.tailorsys.util.PageHelperUtils;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * FileName: DataServiceImpl
 * Author: Li Zihao
 * Date: 2021/3/12 12:32
 * Description:
 */
@Service("dataService")
@Transactional(rollbackFor = Exception.class)
public class DataServiceImpl implements DataService {

    @Autowired
    private ClothTypeMapper clothTypeMapper;
    @Autowired
    private FabricTypeMapper fabricTypeMapper;
    @Autowired
    private ClothConsumptionMapper clothConsumptionMapper;
    @Autowired
    private ClothConsumptionVOMapper clothConsumptionVOMapper;

    /**
     * 与ClothTypeController有关
     *
     */

    @Override
    public int addClothType(ClothType clothType) {
        int res = clothTypeMapper.insert(clothType);
        return res;
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

    /**
     * 与FabricTypeController有关
     *
     */

    @Override
    public int addFabricType(FabricType fabricType){
        int res = fabricTypeMapper.insert(fabricType);
        return res;
    }

    @Override
    public FabricType findFabricTypeById(Integer fabrictypeId){
        FabricType fabricType = fabricTypeMapper.selectByPrimaryKey(fabrictypeId);
        return fabricType;
    }

    public PageResult findFabricTypeList(String fabrictypeName,String fabrictypeCategory, Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<FabricType> fabricTypeList = fabricTypeMapper.selectAll(fabrictypeName,fabrictypeCategory);
        PageInfo<FabricType> fabricTypePageInfo = new PageInfo<FabricType>(fabricTypeList);
        return PageHelperUtils.getPageResult(fabricTypePageInfo);
    }

    @Override
    public int updateFabricType(FabricType fabricType){
        int res = fabricTypeMapper.updateByPrimaryKey(fabricType);
        return res;
    }

    /**
     * 与ClothConsumptionController有关
     *
     */
    @Override
    public ClothConsumption findClothConsumptionById(Integer consumId){
        ClothConsumption clothConsumption = clothConsumptionMapper.selectByPrimaryKey(consumId);
        return clothConsumption;

    }

    @Override
    public PageResult findClothConsumptionList(String query, Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<ClothConsumptionVO> clothConsumptionVOList = clothConsumptionVOMapper.selectAll(query);
        PageInfo<ClothConsumptionVO> clothConsumptionVOPageInfo = new PageInfo<ClothConsumptionVO>(clothConsumptionVOList);
        return PageHelperUtils.getPageResult(clothConsumptionVOPageInfo);
    }

    @Override
    public int updateClothConsumption(ClothConsumption clothConsumption){
        int res = clothConsumptionMapper.updateByPrimaryKey(clothConsumption);
        return res;
    }

    @Override
    public ClothConsumptionVO findClothConsumptionVOById(Integer consumId){
        ClothConsumptionVO clothConsumptionVO = clothConsumptionVOMapper.selectByConsumId(consumId);
        return clothConsumptionVO;
    }

    @Override
    public int addClothConsumption(ClothConsumption clothConsumption){
        int res = clothConsumptionMapper.insert(clothConsumption);
        return res;
    }

}
