package com.xtei.tailorsys.service;

import java.util.List;
import java.util.Map;

/**
 * FileName: StatisticsService
 * Author: Li Zihao
 * Date: 2021/3/29 10:44
 * Description:
 */
public interface StatisticsService {

    List<Map> getFasStockGroupByFabricType();

    List<Map> getQuantityGroupedByFabricType();

    List<Map> getOrderQuantityGroupedByClothType();

    List<Map> getOrderQuantityGroupedByCreateTime(String type);

    List<Map> getOrderQuantityGroupedByCreateTimeWithQuarter();

    List<Map> getTurnoverGroupedByCreateTime(String type);

    List<Map> getTurnoverGroupedByCreateTimeWithQuarter();
}
