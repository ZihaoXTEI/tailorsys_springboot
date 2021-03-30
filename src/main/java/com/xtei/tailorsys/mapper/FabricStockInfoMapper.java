package com.xtei.tailorsys.mapper;

import com.xtei.tailorsys.model.FabricStockInfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FabricStockInfoMapper {

    int deleteByPrimaryKey(@Param("fasId") Integer fasId, @Param("fabricId") Integer fabricId);

    int insert(FabricStockInfo record);

    FabricStockInfo selectByPrimaryKey(@Param("fasId") Integer fasId, @Param("fabricId") Integer fabricId);

    List<FabricStockInfo> selectAll(String query);

    int updateByPrimaryKey(FabricStockInfo record);

    //根据布料编号获取现有库存信息
    double getFasStockByFabricId(Integer fabricId);

    //根据布料编号获取预定用量信息
    double getFasPredeByFabricId(Integer fabricId);

    //根据布料编号获取单位价格信息
    double getUnitPriceByFabricId(Integer fabricId);

    //更新现有库存信息
    int updateStockByFabricId(@Param("fabricId")Integer fabricId,@Param("fasStock") double fasStock, @Param("fasPrede")double fasPrede, @Param("unitPrice")double unitPrice);

    FabricStockInfo selectByFaricId(Integer fabricId);

    List<Map> getFabricInfoLower();

}