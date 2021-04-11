package com.xtei.tailorsys.controller.stock;

import com.xtei.tailorsys.converter.FabricStockInfoConverter;
import com.xtei.tailorsys.entity.FabricInfo;
import com.xtei.tailorsys.entity.FabricStockInfo;
import com.xtei.tailorsys.entity.VO.FabricStockInfoVO;
import com.xtei.tailorsys.entity.response.ResponseBean;
import com.xtei.tailorsys.service.FabricStockService;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * FileName: FabricStockController
 * Author: Li Zihao
 * Date: 2021/2/21 14:03
 * Description:布料库存管理页面
 */
@RestController
@RequestMapping("fabricstock")
public class FabricStockController {

    @Resource
    private FabricStockService fabricStockService;


    /**
     * 获取布料库存信息列表
     * this.queryInfo.fabricname = ''
     * this.queryInfo.fabrictype = ''
     * this.queryInfo.ordertype = 'ASC'
     * this.queryInfo.orderfield = ''
     * this.queryInfo.min = null
     * this.queryInfo.max = null
     */
    @GetMapping("/")
    public ResponseBean<PageResult> getFabricStockInfoList(@RequestParam(value = "fabricname", defaultValue = "") String fabricname,
                                                           @RequestParam(value = "fabrictype", defaultValue = "0") Integer fabrictype,
                                                           @RequestParam(value = "ordertype", defaultValue = "ASC") String ordertype,
                                                           @RequestParam(value = "orderfield", defaultValue = "") String orderfield,
                                                           @RequestParam(value = "min", defaultValue = "") Double min,
                                                           @RequestParam(value = "max", defaultValue = "") Double max,
                                                           @RequestParam(value = "pagenum", defaultValue = "1") Integer pagenum,
                                                           @RequestParam(value = "pagesize", defaultValue = "15") Integer pagesize) {
        PageResult pageResult = fabricStockService.findFabricStockInfoVOList(fabricname,fabrictype,ordertype,orderfield,min,max, pagenum, pagesize);
        if (pageResult != null) {
            return ResponseBean.success("获取布料库存信息成功", pageResult);
        } else {
            return ResponseBean.error("获取布料库存信息失败");
        }

    }

    /**
     * 根据fasId获取布料库存信息
     */
    @GetMapping("/{fasid}")
    public ResponseBean getFabricStockInfo(@PathVariable("fasid") Integer fasId) {
        FabricStockInfoVO fabricStockInfoVO = fabricStockService.findFabricStockInfoVOByFasId(fasId);
        if (fabricStockInfoVO != null) {
            return ResponseBean.success("获取布料库存信息成功", fabricStockInfoVO);
        } else {
            return ResponseBean.error("获取布料库存信息失败");
        }
    }

    /**
     * 修改布料库存信息和布料信息
     */
    @PutMapping("/{fasid}")
    public ResponseBean updateFabricStockInfo(@PathVariable("fasid") Integer fasId, @RequestBody FabricStockInfoVO fabricStockInfoVO) {
        FabricStockInfo fabricStockInfo = FabricStockInfoConverter.converterToFabricStockInfo(fabricStockInfoVO);
        FabricInfo fabricInfo = FabricStockInfoConverter.converterToFabricInfo(fabricStockInfoVO);
        System.out.println(fabricStockInfo);
        System.out.println(fabricInfo);

        try {
            int res = fabricStockService.updateFabricStockInfo(fabricStockInfo, fabricInfo);
            if (res == 2) {
                return ResponseBean.success("修改布料库存信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("修改布料库存信息错误,请联系系统管理员");
        }
        return ResponseBean.error("修改布料库存信息失败");
    }

}
