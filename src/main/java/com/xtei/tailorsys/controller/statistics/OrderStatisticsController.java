package com.xtei.tailorsys.controller.statistics;

import com.xtei.tailorsys.entity.response.ResponseBean;
import com.xtei.tailorsys.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * FileName: OrderStatisticsController
 * Author: Li Zihao
 * Date: 2021/3/29 20:37
 * Description: 订单统计页面视图控制器
 */
@RestController
@RequestMapping("statistics/order")
public class OrderStatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    /**
     * 获取订单服装种类统计数据
     *
     */
    @GetMapping("/data1")
    public ResponseBean getOrderQuantityGroupedByClothType() {
        try {
            List<Map> dataList = statisticsService.getOrderQuantityGroupedByClothType();
            if (dataList != null) {
                return ResponseBean.success("获取订单服装种类统计数据成功", HttpServletResponse.SC_PARTIAL_CONTENT, dataList);
            } else {
                return ResponseBean.error("获取订单服装种类统计数据失败", HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("获取订单服装种类统计数据失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * 按指定时间获取订单数量统计数据
     * 1:本年每月
     * 2:本年每季度
     * 3:按年
     */
    @GetMapping("/data2/{type}")
    public ResponseBean getOrderQuantityGroupedByCreateTime(@PathVariable("type") Integer type) {
        String str = "";
        List<Map> dataList = null;
        try {

            if (type == 1) {
                str = "'%Y年%m月'";
                dataList = statisticsService.getOrderQuantityGroupedByCreateTime(str);
            } else if (type == 3) {
                str = "'%Y年'";
                dataList = statisticsService.getOrderQuantityGroupedByCreateTime(str);
            } else if (type == 2) {
                dataList = statisticsService.getOrderQuantityGroupedByCreateTimeWithQuarter();
            } else {
                return ResponseBean.error("请求格式错误", HttpServletResponse.SC_BAD_REQUEST);
            }

            if (dataList != null) {
                return ResponseBean.success("获取订单数量统计数据成功", HttpServletResponse.SC_PARTIAL_CONTENT, dataList);
            } else {
                return ResponseBean.error("获取订单数量统计数据为空", HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("获取订单数量统计数据失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 按指定时间获取营业额统计数据
     * 1:本年每月
     * 2:本年每季度
     * 3:按年
     */
    @GetMapping("data3/{type}")
    public ResponseBean getTurnoverGroupedByCreateTime(@PathVariable("type") Integer type) {
        String str = "";
        List<Map> dataList = null;
        try {
            if (type == 1) {
                str = "'%Y年%m月'";
                dataList = statisticsService.getTurnoverGroupedByCreateTime(str);
            } else if (type == 3) {
                str = "'%Y年'";
                dataList = statisticsService.getTurnoverGroupedByCreateTime(str);
            } else if (type == 2) {
                dataList = statisticsService.getTurnoverGroupedByCreateTimeWithQuarter();
            } else {
                return ResponseBean.error("请求格式错误",HttpServletResponse.SC_BAD_REQUEST);
            }
            if (dataList != null) {
                return ResponseBean.success("获取营业额统计数据成功",HttpServletResponse.SC_PARTIAL_CONTENT, dataList);
            } else {
                return ResponseBean.error("获取营业额统计数据为空",HttpServletResponse.SC_NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("获取营业额统计数据失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
