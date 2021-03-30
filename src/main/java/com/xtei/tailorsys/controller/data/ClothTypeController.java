package com.xtei.tailorsys.controller.data;

import com.xtei.tailorsys.model.ClothType;
import com.xtei.tailorsys.model.VO.ClothConsumptionVO;
import com.xtei.tailorsys.model.response.ResponseBean;
import com.xtei.tailorsys.service.ClothTypeService;
import com.xtei.tailorsys.service.DataService;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * FileName: ClothTypeController
 * Author: Li Zihao
 * Date: 2021/2/25 11:17
 * Description:
 */
@RestController
@RequestMapping("data/clothtype")
public class ClothTypeController {

    @Resource
    private DataService dataService;

    /**
     * 增加服装类型信息
     */
    @PostMapping(value = "/")
    public ResponseBean addClothType(@RequestBody ClothType clothType){
        System.out.println(clothType);
        if(clothType.getClothtypeName() == null || clothType.getClothtypeName().trim() == ""){
            return ResponseBean.error("服装类型名称不能为空");
        }

        if(dataService.addClothType(clothType) == 1){
            return ResponseBean.success("新增服装类型信息成功");
        }else {
            return ResponseBean.error("新增服装类型信息失败");
        }

    }

    /**
     * 修改服装类型信息
     */
    @PutMapping(value = "/{clothtypeid}")
    public ResponseBean updateClothType(@PathVariable("clothtypeid")Integer clothTypeId, @RequestBody ClothType clothType){
        ClothType clothTypeV = clothType;
        clothTypeV.setClothtypeId(clothTypeId);
        if(dataService.updateClothType(clothTypeV) == 1){
            return ResponseBean.success("修改服装类型信息成功");
        }else {
            return ResponseBean.error("修改服装类型信息失败");
        }
    }

    /**
     * 根据编号获取衣服类型信息
     */
    @GetMapping(value = "/{clothtypeid}")
    public ResponseBean getClothTypeById(@PathVariable("clothtypeid")Integer clothtypeId){
        ClothType clothType = dataService.findClothTypeById(clothtypeId);
        if(clothType != null){
            return ResponseBean.success("获取服装类型信息成功",clothType);
        }else {
            return ResponseBean.error("获取服装类型信息失败");
        }
    }

    /**
     * 获取衣服类型信息列表
     */
    @GetMapping("/")
    public ResponseBean<PageResult> getClothTypeList(@RequestParam(value = "query", defaultValue = "") String query,
                                                     @RequestParam(value = "pagenum", defaultValue = "1") Integer pagenum,
                                                     @RequestParam(value = "pagesize", defaultValue = "10") Integer pagesize) {
        PageResult pageResult = dataService.findClothTypeList(query, pagenum, pagesize);
        if(pageResult != null ){
            return ResponseBean.success("获取衣服类型列表成功",pageResult);
        }else {
            return ResponseBean.error("获取衣服类型列表失败",null);
        }
    }

}
