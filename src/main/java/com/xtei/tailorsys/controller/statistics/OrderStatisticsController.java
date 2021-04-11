package com.xtei.tailorsys.controller.statistics;

import com.xtei.tailorsys.entity.response.ResponseBean;
import com.xtei.tailorsys.service.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * FileName: OrderStatisticsController
 * Author: Li Zihao
 * Date: 2021/3/29 20:37
 * Description:
 */
@RestController
@RequestMapping("statistics/order")
public class OrderStatisticsController {

    @Resource
    private StatisticsService statisticsService;

    @GetMapping("/data1")
    public ResponseBean getOrderQuantityGroupedByClothType() {
        List<Map> dataList = statisticsService.getOrderQuantityGroupedByClothType();
        if (dataList != null) {
            return ResponseBean.success("获取数据成功", dataList);
        } else {
            return ResponseBean.error("获取数据失败");
        }
    }

    /**
     * 1:本年每月
     * 2:本年每季度
     * 3:按年
     *
     * @param type
     * @return
     */
    @GetMapping("/data2/{type}")
    public ResponseBean getOrderQuantityGroupedByCreateTime(@PathVariable("type") Integer type) {
        String str = "";
        List<Map> dataList = null;
        if (type == 1) {
            str = "'%Y年%m月'";
            dataList = statisticsService.getOrderQuantityGroupedByCreateTime(str);
        } else if (type == 3) {
            str = "'%Y年'";
            dataList = statisticsService.getOrderQuantityGroupedByCreateTime(str);
        } else if (type == 2) {
            dataList = statisticsService.getOrderQuantityGroupedByCreateTimeWithQuarter();
        } else {
            return ResponseBean.error("请求格式错误");
        }
        if (dataList != null) {
            return ResponseBean.success("获取数据成功", dataList);
        } else {
            return ResponseBean.error("获取数据失败");
        }
    }

    @GetMapping("data3/{type}")
    public ResponseBean getTurnoverGroupedByCreateTime(@PathVariable("type")Integer type){
        String str = "";
        List<Map> dataList = null;
        if (type == 1) {
            str = "'%Y年%m月'";
            dataList = statisticsService.getTurnoverGroupedByCreateTime(str);
        } else if (type == 3) {
            str = "'%Y年'";
            dataList = statisticsService.getTurnoverGroupedByCreateTime(str);
        } else if (type == 2) {
            dataList = statisticsService.getTurnoverGroupedByCreateTimeWithQuarter();
        } else {
            return ResponseBean.error("请求格式错误");
        }
        if (dataList != null) {
            return ResponseBean.success("获取数据成功", dataList);
        } else {
            return ResponseBean.error("获取数据失败");
        }
    }
}
