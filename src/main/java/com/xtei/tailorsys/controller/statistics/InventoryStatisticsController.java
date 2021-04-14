package com.xtei.tailorsys.controller.statistics;

import com.xtei.tailorsys.entity.response.ResponseBean;
import com.xtei.tailorsys.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * FileName: InventoryController
 * Author: Li Zihao
 * Date: 2021/3/29 10:41
 * Description: 布料库存统计页面视图控制器
 */
@RestController
@RequestMapping("statistics/inventory")
public class InventoryStatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/fasstock")
    public ResponseBean getFasStockGroupByFabricType() {

        try{
            List<Map> dataList = statisticsService.getFasStockGroupByFabricType();
            if (dataList != null) {
                return ResponseBean.success("获取布料库存统计数据成功", HttpServletResponse.SC_PARTIAL_CONTENT, dataList);
            } else {
                return ResponseBean.error("获取布料库存统计数据为空", HttpServletResponse.SC_NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("获取布料库存统计数据错误", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/quantity")
    public ResponseBean getQuantityGroupedByFabricType() {
        try {
            List<Map> dataList = statisticsService.getQuantityGroupedByFabricType();
            if (dataList != null) {
                return ResponseBean.success("获取布料种类统计数据成功", HttpServletResponse.SC_PARTIAL_CONTENT, dataList);
            } else {
                return ResponseBean.error("获取布料种类统计数据为空", HttpServletResponse.SC_NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("获取布料种类统计数据错误", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }
}
