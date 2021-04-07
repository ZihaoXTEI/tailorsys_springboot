package com.xtei.tailorsys.controller;

import com.xtei.tailorsys.model.ClothType;
import com.xtei.tailorsys.model.Permission;
import com.xtei.tailorsys.model.VO.FabricTypeVO;
import com.xtei.tailorsys.model.response.ResponseBean;
import com.xtei.tailorsys.service.ClothTypeService;
import com.xtei.tailorsys.service.ViewService;
import com.xtei.tailorsys.util.FormatUtils;
import com.xtei.tailorsys.util.HttpStatus;
import com.xtei.tailorsys.util.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * FileName: ViewController
 * Author: Li Zihao
 * Date: 2021/2/10 20:32
 * Description: 视图控制器
 */

@RestController
@RequestMapping("view")
public class ViewController {

    @Autowired
    private ViewService viewService;

    @Autowired
    private ClothTypeService clothTypeService;

    /**
     * 获取菜单数据接口
     */
    @GetMapping("/menus")
    public ResponseBean getMenus() {
        List<Permission> permissionList = viewService.findPermissionList();
        if (permissionList != null) {
            return ResponseBean.success("获取菜单数据成功", HttpServletResponse.SC_PARTIAL_CONTENT, permissionList);
        } else {
            return ResponseBean.error("获取菜单数据失败", HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * 获取布料信息
     */
    @GetMapping("/fabricinfo/{query}")
    public ResponseBean getFabricInfoSelect(@PathVariable(value = "query")String query) {
        List<Map<Integer, String>> fabricInfoMap = viewService.findFabricInfoMap(query);
        if (fabricInfoMap != null) {
            return ResponseBean.success("获取布料信息数据成功",HttpServletResponse.SC_PARTIAL_CONTENT, fabricInfoMap);
        } else {
            return ResponseBean.error("获取布料信息数据失败",HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @GetMapping("/fabricinfo")
    public ResponseBean getFabricInfoSelect() {
        String query = "";
        List<Map<Integer, String>> fabricInfoMap = viewService.findFabricInfoMap(query);
        if (fabricInfoMap != null) {
            return ResponseBean.success("获取布料信息数据成功",HttpServletResponse.SC_PARTIAL_CONTENT, fabricInfoMap);
        } else {
            return ResponseBean.error("获取布料信息数据失败", HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * 获取布料类型信息
     */
    @GetMapping("/fabrictype")
    public ResponseBean getFabricTypeSelect() {
        List<FabricTypeVO> fabricTypeMap = viewService.findFabricTypeMap();
        if (fabricTypeMap != null) {
            System.out.println(fabricTypeMap);
            return ResponseBean.success("获取布料类型数据成功",HttpServletResponse.SC_PARTIAL_CONTENT, fabricTypeMap);
        } else {
            return ResponseBean.error("获取布料类型数据失败", HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * 获取供应商信息
     */
    @GetMapping("/supplier")
    public ResponseBean getSupplierSelect() {
        List<Map<Integer, String>> supplierMap = viewService.findSupplierMap();
        if (supplierMap != null) {
            return ResponseBean.success("获取供应商数据成功",HttpServletResponse.SC_PARTIAL_CONTENT, supplierMap);
        } else {
            return ResponseBean.error("获取供应商数据失败", HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/user")
    public ResponseBean getUserSelect() {
        List<Map<Integer, String>> userMap = viewService.findUserMap();
        if (userMap != null) {
            return ResponseBean.success("获取用户数据成功",HttpServletResponse.SC_PARTIAL_CONTENT, userMap);
        } else {
            return ResponseBean.error("获取用户数据失败", HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * 获取服装类型信息
     */
    @GetMapping("/clothtype")
    public ResponseBean getClothTypeSelect() {
        List<Map<Integer, String>> clothTypeMap = viewService.findClothTypeMap();
        if (clothTypeMap != null) {
            return ResponseBean.success("获取服装类型数据成功",HttpServletResponse.SC_PARTIAL_CONTENT, clothTypeMap);
        } else {
            return ResponseBean.error("获取服装类型数据失败", HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * 获取顾客信息
     */
    @GetMapping("/customer")
    public ResponseBean getCustomerSelect(){
        List<Map<Integer,String>> customerMap = viewService.findCustomerMap();
        if(customerMap != null){
            return ResponseBean.success("获取顾客信息成功",HttpServletResponse.SC_PARTIAL_CONTENT,customerMap);
        }else {
            return ResponseBean.error("获取顾客信息失败",HttpServletResponse.SC_NOT_FOUND);
        }
    }


    /**
     * 获取量体数据信息
     */
    @GetMapping("/anthropometry/{customerid}")
    public ResponseBean getAnthropometrySelect(@PathVariable("customerid") Integer customerId){
        List<Map<Integer,String>> anthropometryMap = viewService.findAnthropometryMap(customerId);
        if(anthropometryMap != null){
            return ResponseBean.success("获取信息成功",HttpServletResponse.SC_PARTIAL_CONTENT,anthropometryMap);
        }else {
            return ResponseBean.error("获取信息失败",HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * 获取ClothType表的列名和列注释
     */
    @GetMapping("/clothtypecolumn")
    public ResponseBean getClothtypeColumnInfo() {
        List<Map<String, String>> clothtypeColumnInfoList = viewService.findClothtypeColumnInfo();
        ArrayList<Map<String,String>> columnNames = FormatUtils.transforColumnName(clothtypeColumnInfoList);
        if (columnNames != null) {
            return ResponseBean.success("获取信息成功",HttpServletResponse.SC_PARTIAL_CONTENT, columnNames);
        } else {
            return ResponseBean.error("获取信息失败",HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * 获取ClothType表的列名和列注释
     */
    @GetMapping("/clothconsumptioncolumn")
    public ResponseBean getClothConsumptionColumnInfo() {
        List<Map<String, String>> clothConsumptionColumnInfoList = viewService.findClothconsumptionColumnInfo();
        ArrayList<Map<String,String>> columnNames = FormatUtils.transforColumnName(clothConsumptionColumnInfoList);
        if (columnNames != null) {
            return ResponseBean.success("获取信息成功",HttpServletResponse.SC_PARTIAL_CONTENT, columnNames);
        } else {
            return ResponseBean.error("获取信息失败",HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * 获取Anthropometric表的列名和列注释
     */
    @GetMapping("/anthropometric")
    public ResponseBean getAnthropometricColumnInfo(){
        try {
            List<Map<String, String>> anthropometricColumnInfo = viewService.findAnthropometricColumnInfo();
            ArrayList<Map<String, String>> columnNames = FormatUtils.transforColumnName(anthropometricColumnInfo);

            if (columnNames != null) {
                return ResponseBean.success("获取信息成功", HttpServletResponse.SC_PARTIAL_CONTENT, columnNames);
            } else {
                return ResponseBean.error("获取信息失败", HttpServletResponse.SC_NOT_FOUND);
            }
        }catch (Exception e){
            return ResponseBean.error("获取数据错误",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据衣服类型获取量体所需数据
     */
    @GetMapping("anthrdata/{clothtype}")
    public ResponseBean getAnthropometricDataByClothType(@PathVariable("clothtype") Integer clothtypeId){
        try {
            ClothType clothType = clothTypeService.findClothTypeById(clothtypeId);
            ArrayList<Map<String, String>> columnInfo = FormatUtils.transforColumnName(viewService.findClothtypeColumnInfo());
            ArrayList<Map<String, String>> arrayList = ReflectionUtils.getAnthropometricData(clothType, columnInfo);
            //System.out.println("XXX"+arrayList);
            return ResponseBean.success("获取数据成功", HttpServletResponse.SC_PARTIAL_CONTENT, arrayList);
        }catch (Exception e){
            return ResponseBean.error("获取数据错误",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
