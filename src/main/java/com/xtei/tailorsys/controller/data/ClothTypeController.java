package com.xtei.tailorsys.controller.data;

import com.xtei.tailorsys.entity.ClothType;
import com.xtei.tailorsys.entity.response.ResponseBean;
import com.xtei.tailorsys.service.DataService;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * FileName: ClothTypeController
 * Author: Li Zihao
 * Date: 2021/2/25 11:17
 * Description: 服装类型视图控制器
 */
@RestController
@RequestMapping("data/clothtype")
public class ClothTypeController {

    @Autowired
    private DataService dataService;

    /**
     * 增加服装类型信息
     */
    @PostMapping(value = "/")
    public ResponseBean addClothType(@RequestBody ClothType clothType) {
        if (clothType.getClothtypeName() == null || clothType.getClothtypeName().trim() == "") {
            return ResponseBean.error("服装类型名称不能为空", HttpServletResponse.SC_BAD_REQUEST);
        }

        try {
            dataService.addClothType(clothType);
            return ResponseBean.success("新增服装类型信息成功", HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("新增服装类型信息失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 修改服装类型信息
     */
    @PutMapping(value = "/{clothtypeid}")
    public ResponseBean updateClothType(@PathVariable("clothtypeid") Integer clothTypeId, @RequestBody ClothType clothType) {
        ClothType clothTypeV = clothType;
        clothTypeV.setClothtypeId(clothTypeId);
        try {
            dataService.updateClothType(clothTypeV);
            return ResponseBean.success("修改服装类型信息成功", HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("修改服装类型信息失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据编号获取衣服类型信息
     */
    @GetMapping(value = "/{clothtypeid}")
    public ResponseBean getClothTypeById(@PathVariable("clothtypeid") Integer clothtypeId) {
        try {
            ClothType clothType = dataService.findClothTypeById(clothtypeId);
            if(clothType != null) {
                return ResponseBean.success("获取服装类型信息成功", HttpServletResponse.SC_PARTIAL_CONTENT, clothType);
            }else {
                return ResponseBean.success("获取服装类型信息为空", HttpServletResponse.SC_NOT_FOUND, clothType);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("获取服装类型信息错误",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取衣服类型信息列表
     */
    @GetMapping("/")
    public ResponseBean<PageResult> getClothTypeList(@RequestParam(value = "query", defaultValue = "") String query,
                                                     @RequestParam(value = "pagenum", defaultValue = "1") Integer pagenum,
                                                     @RequestParam(value = "pagesize", defaultValue = "10") Integer pagesize) {
        try {
            PageResult pageResult = dataService.findClothTypeList(query, pagenum, pagesize);
            if(pageResult != null) {
                return ResponseBean.success("获取衣服类型列表成功", HttpServletResponse.SC_OK, pageResult);
            }else {
                return ResponseBean.success("获取衣服类型列表为空", HttpServletResponse.SC_NOT_FOUND, pageResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("获取衣服类型列表错误",HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
        }
    }

}
