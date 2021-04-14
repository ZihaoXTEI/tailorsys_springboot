package com.xtei.tailorsys.controller.data;

import com.xtei.tailorsys.entity.FabricType;
import com.xtei.tailorsys.entity.response.ResponseBean;
import com.xtei.tailorsys.service.DataService;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * FileName: FabricTypeController
 * Author: Li Zihao
 * Date: 2021/2/25 11:17
 * Description: 布料类型视图控制器
 */

@RestController
@RequestMapping("data/fabrictype")
public class FabricTypeController {

    @Autowired
    private DataService dataService;

    /**
     * 增加布料类型信息
     */
    @PostMapping(value = "/")
    public ResponseBean addFabricType(@RequestBody FabricType fabricType) {
        if (fabricType.getFabrictypeName() == null || fabricType.getFabrictypeName().trim() == "") {
            return ResponseBean.error("布料类型名称不能为空", HttpServletResponse.SC_BAD_REQUEST);
        }
        try {
            dataService.addFabricType(fabricType);
            return ResponseBean.success("新增布料类型信息成功", HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            return ResponseBean.error("新增布料类型信息失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 修改类型布料信息
     */
    @PutMapping(value = "/{fabrictypeid}")
    public ResponseBean updateFabricType(@PathVariable("fabrictypeid") Integer fabrictypeId, @RequestBody FabricType fabricType) {
        FabricType fabricTypeV = fabricType;
        fabricTypeV.setFabrictypeId(fabrictypeId);
        try {
            dataService.updateFabricType(fabricTypeV);
            return ResponseBean.success("修改布料类型信息成功", HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            return ResponseBean.error("修改布料类型信息失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据编号获取布料类型信息
     */
    @GetMapping(value = "/{fabrictypeid}")
    public ResponseBean getFabricTypeById(@PathVariable("fabrictypeid") Integer fabrictypeId) {
        try {
            FabricType fabricType = dataService.findFabricTypeById(fabrictypeId);
            if(fabricType != null) {
                return ResponseBean.success("获取布料类型信息成功", HttpServletResponse.SC_OK, fabricType);
            }else {
                return ResponseBean.success("获取布料类型信息为空", HttpServletResponse.SC_NOT_FOUND, fabricType);
            }
        } catch (Exception e) {
            return ResponseBean.error("获取布料类型信息错误", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取布料类型信息列表
     */
    @GetMapping(value = "/")
    public ResponseBean<PageResult> getFabricTypeList(@RequestParam(value = "fabrictypename", defaultValue = "") String fabrictypeName,
                                                      @RequestParam(value = "fabrictypecategory", defaultValue = "") String fabrictypeCategory,
                                                      @RequestParam(value = "pagenum", defaultValue = "1") Integer pagenum,
                                                      @RequestParam(value = "pagesize", defaultValue = "10") Integer pagesize) {
        try {
            PageResult pageResult = dataService.findFabricTypeList(fabrictypeName, fabrictypeCategory, pagenum, pagesize);
            if(pageResult != null) {
                return ResponseBean.success("获取布料类型信息成功", HttpServletResponse.SC_OK, pageResult);
            }else {
                return ResponseBean.success("获取布料类型信息为空", HttpServletResponse.SC_NOT_FOUND, pageResult);
            }
        } catch (Exception e) {
            return ResponseBean.error("获取布料类型信息错误", HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
        }
    }
}
