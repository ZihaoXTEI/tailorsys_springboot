package com.xtei.tailorsys.controller.statistics;

import com.xtei.tailorsys.entity.response.ResponseBean;
import com.xtei.tailorsys.service.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * FileName: InventoryController
 * Author: Li Zihao
 * Date: 2021/3/29 10:41
 * Description:
 */
@RestController
@RequestMapping("statistics/inventory")
public class InventoryStatisticsController {

    @Resource
    private StatisticsService statisticsService;

    @GetMapping("/fasstock")
    public ResponseBean getFasStockGroupByFabricType(){
        List<Map> dataList = statisticsService.getFasStockGroupByFabricType();
        if(dataList != null){
            return ResponseBean.success("获取数据成功",dataList);
        }else {
            return ResponseBean.error("获取数据失败");
        }
    }

    @GetMapping("/quantity")
    public ResponseBean getQuantityGroupedByFabricType(){
        List<Map> dataList = statisticsService.getQuantityGroupedByFabricType();
        if(dataList != null){
            return ResponseBean.success("获取数据成功",dataList);
        }else {
            return ResponseBean.error("获取数据失败");
        }
    }
}
