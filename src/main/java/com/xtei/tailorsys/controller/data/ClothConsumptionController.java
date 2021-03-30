package com.xtei.tailorsys.controller.data;

import com.github.pagehelper.dialect.ReplaceSql;
import com.xtei.tailorsys.model.ClothConsumption;
import com.xtei.tailorsys.model.VO.ClothConsumptionVO;
import com.xtei.tailorsys.model.response.ResponseBean;
import com.xtei.tailorsys.service.DataService;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * FileName: ClothQuantityController
 * Author: Li Zihao
 * Date: 2021/3/12 11:19
 * Description:
 */
@RestController
@RequestMapping("data/clothconsumption")
public class ClothConsumptionController {

    @Resource
    private DataService dataService;

    /**
     * 修改服装用料信息
     */
    @PutMapping(value = "/{consumid}")
    public ResponseBean updateClothConsumption(@PathVariable("consumid") Integer consumId, @RequestBody ClothConsumption clothConsumption) {
        ClothConsumption clothConsumptionV = clothConsumption;
        clothConsumptionV.setConsumId(consumId);
        if (dataService.updateClothConsumption(clothConsumptionV) == 1) {
            return ResponseBean.success("修改服装用料信息成功");
        } else {
            return ResponseBean.error("修改服装用料信息失败");
        }
    }

    /**
     * 新增服装用料信息
     */
    @PostMapping(value = "/")
    public ResponseBean addClothConsumption(@RequestBody ClothConsumption clothConsumption){
        System.out.println(clothConsumption);

        if(dataService.addClothConsumption(clothConsumption) == 1){
            return ResponseBean.success("新增服装用料信息成功");
        }else {
            return ResponseBean.error("新增服装用料信息失败");
        }
    }

    /**
     * 根据编号获取服装用料信息
     */
    @GetMapping(value = "/{consumid}")
    public ResponseBean getClothConsumptionById(@PathVariable("consumid") Integer consumId) {
        ClothConsumption clothConsumption = dataService.findClothConsumptionById(consumId);
        if (clothConsumption != null) {
            return ResponseBean.success("获取服装用料信息成功", clothConsumption);
        } else {
            return ResponseBean.error("获取服装用料信息失败");
        }
    }

    /**
     * 获取服装用料列表
     */
    @GetMapping(value = "/")
    public ResponseBean<PageResult> getClothConsumptionList(@RequestParam(value = "query", defaultValue = "") String query,
                                                            @RequestParam(value = "pagenum", defaultValue = "1") Integer pagenum,
                                                            @RequestParam(value = "pagesize", defaultValue = "10") Integer pagesize) {
        PageResult pageResult = dataService.findClothConsumptionList(query, pagenum, pagesize);
        if(pageResult !=null){
            return ResponseBean.success("获取服装用料信息成功",pageResult);
        }else {
            return ResponseBean.error("获取服装用料信息失败",null);
        }
    }
}
