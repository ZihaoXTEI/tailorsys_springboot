package com.xtei.tailorsys.controller.data;

import com.github.pagehelper.dialect.ReplaceSql;
import com.xtei.tailorsys.model.FabricType;
import com.xtei.tailorsys.model.response.ResponseBean;
import com.xtei.tailorsys.service.DataService;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * FileName: FabricTypeController
 * Author: Li Zihao
 * Date: 2021/2/25 11:17
 * Description:
 */

@RestController
@RequestMapping("data/fabrictype")
public class FabricTypeController {

    @Resource
    private DataService dataService;

    /**
     * 增加布料类型信息
     */
    @PostMapping(value = "/")
    public ResponseBean addFabricType(@RequestBody FabricType fabricType){
        if(fabricType.getFabrictypeName() == null || fabricType.getFabrictypeName().trim() == ""){
            return ResponseBean.error("布料类型名称不能为空");
        }

        if(dataService.addFabricType(fabricType) == 1){
            return ResponseBean.success("新增布料类型信息成功");
        }else {
            return ResponseBean.error("新增布料类型信息失败");
        }
    }

    /**
     * 修改类型布料信息
     */
    @PutMapping(value = "/{fabrictypeid}")
    public ResponseBean updateFabricType(@PathVariable("fabrictypeid")Integer fabrictypeId,@RequestBody FabricType fabricType){
        FabricType fabricTypeV = fabricType;
        fabricTypeV.setFabrictypeId(fabrictypeId);
        if(dataService.updateFabricType(fabricTypeV) == 1){
            return ResponseBean.success("修改布料类型信息成功");
        }else {
            return ResponseBean.error("修改布料类型信息失败");
        }
    }

    /**
     * 根据编号获取布料类型信息
     */
    @GetMapping(value = "/{fabrictypeid}")
    public ResponseBean getFabricTypeById(@PathVariable("fabrictypeid")Integer fabrictypeId){
        FabricType fabricType = dataService.findFabricTypeById(fabrictypeId);
        if(fabricType != null){
            return ResponseBean.success("获取布料类型信息成功",fabricType);
        }else {
            return ResponseBean.error("获取布料类型信息失败");
        }
    }

    /**
     * 获取布料类型信息列表
     */
    @GetMapping(value = "/")
    public ResponseBean<PageResult> getFabricTypeList(@RequestParam(value = "fabrictypename", defaultValue = "") String fabrictypeName,
                                                      @RequestParam(value = "fabrictypecategory",defaultValue = "")String fabrictypeCategory,
                                                      @RequestParam(value = "pagenum", defaultValue = "1") Integer pagenum,
                                                      @RequestParam(value = "pagesize", defaultValue = "10") Integer pagesize) {
        PageResult pageResult = dataService.findFabricTypeList(fabrictypeName,fabrictypeCategory, pagenum, pagesize);
        if(pageResult != null){
            return ResponseBean.success("获取布料类型信息成功",pageResult);
        }else {
            return ResponseBean.error("获取布料类型信息失败",null);
        }
    }
    }
