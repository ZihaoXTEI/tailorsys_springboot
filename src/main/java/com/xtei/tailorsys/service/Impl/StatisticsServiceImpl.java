package com.xtei.tailorsys.service.Impl;

import com.xtei.tailorsys.mapper.VO.FabricStockInfoVOMapper;
import com.xtei.tailorsys.mapper.VO.OrderViewVOMapper;
import com.xtei.tailorsys.service.StatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * FileName: StatisticsServiceImpl
 * Author: Li Zihao
 * Date: 2021/3/29 10:45
 * Description:
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Resource
    private FabricStockInfoVOMapper fabricStockInfoVOMapper;
    @Resource
    private OrderViewVOMapper orderViewVOMapper;

    @Override
    public List<Map> getFasStockGroupByFabricType(){
        List<Map> dataList = fabricStockInfoVOMapper.getFasStockGroupByFabricType();
        return dataList;
    }

    @Override
    public List<Map> getQuantityGroupedByFabricType(){
        List<Map> dataList = fabricStockInfoVOMapper.getQuantityGroupedByFabricType();
        return dataList;
    }

    @Override
    public List<Map> getOrderQuantityGroupedByClothType(){
        List<Map> dataList = orderViewVOMapper.getOrderQuantityGroupedByClothType();
        return dataList;
    }

    @Override
    public List<Map> getOrderQuantityGroupedByCreateTime(String type){
        List<Map> dataList = orderViewVOMapper.getOrderQuantityGroupedByCreateTime(type);
        return dataList;
    }

    @Override
    public List<Map> getOrderQuantityGroupedByCreateTimeWithQuarter(){
        List<Map> dataList = orderViewVOMapper.getOrderQuantityGroupedByCreateTimeWithQuarter();
        return dataList;
    }

    @Override
    public List<Map> getTurnoverGroupedByCreateTime(String type){
        List<Map> dataList = orderViewVOMapper.getTurnoverGroupedByCreateTime(type);
        return dataList;
    }

    @Override
    public List<Map> getTurnoverGroupedByCreateTimeWithQuarter(){
        List<Map> dataList = orderViewVOMapper.getTurnoverGroupedByCreateTimeWithQuarter();
        return dataList;
    }
}
