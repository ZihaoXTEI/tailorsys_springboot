package com.xtei.tailorsys.controller;

import com.xtei.tailorsys.model.ClothType;
import com.xtei.tailorsys.model.Permission;
import com.xtei.tailorsys.model.VO.FabricTypeVO;
import com.xtei.tailorsys.model.response.ResponseBean;
import com.xtei.tailorsys.service.ClothTypeService;
import com.xtei.tailorsys.service.ViewService;
import com.xtei.tailorsys.util.FormatUtils;
import com.xtei.tailorsys.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @Resource
    private ViewService viewService;

    @Resource
    private ClothTypeService clothTypeService;

    /**
     * 获取菜单数据接口
     */
    @GetMapping("/menus/")
    public ResponseBean getMenus() {
        List<Permission> permissionList = viewService.findPermissionList();
        if (permissionList != null) {
            return ResponseBean.success("获取菜单数据成功", permissionList);
        } else {
            return ResponseBean.error("获取菜单数据失败", null);
        }
    }

    /**
     * 获取布料信息
     */
    @GetMapping("/fabricinfo/{query}")
    public ResponseBean getFabricInfoSelect(@PathVariable(value = "query")String query) {
        List<Map<Integer, String>> fabricInfoMap = viewService.findFabricInfoMap(query);
        if (fabricInfoMap != null) {
            return ResponseBean.success("获取布料信息数据成功", fabricInfoMap);
        } else {
            return ResponseBean.error("获取布料信息数据失败", null);
        }
    }

    @GetMapping("/fabricinfo/")
    public ResponseBean getFabricInfoSelect() {
        String query = "";
        List<Map<Integer, String>> fabricInfoMap = viewService.findFabricInfoMap(query);
        if (fabricInfoMap != null) {
            return ResponseBean.success("获取布料信息数据成功", fabricInfoMap);
        } else {
            return ResponseBean.error("获取布料信息数据失败", null);
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
            return ResponseBean.success("获取布料类型数据成功", fabricTypeMap);
        } else {
            return ResponseBean.error("获取布料类型数据失败", null);
        }
    }

    /**
     * 获取供应商信息
     */
    @GetMapping("/supplier")
    public ResponseBean getSupplierSelect() {
        List<Map<Integer, String>> supplierMap = viewService.findSupplierMap();
        if (supplierMap != null) {
            return ResponseBean.success("获取供应商数据成功", supplierMap);
        } else {
            return ResponseBean.error("获取供应商数据成功", null);
        }
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/user")
    public ResponseBean getUserSelect() {
        List<Map<Integer, String>> userMap = viewService.findUserMap();
        if (userMap != null) {
            return ResponseBean.success("获取用户数据成功", userMap);
        } else {
            return ResponseBean.error("获取用户数据失败", null);
        }
    }

    /**
     * 获取服装类型信息
     */
    @GetMapping("/clothtype")
    public ResponseBean getClothTypeSelect() {
        List<Map<Integer, String>> clothTypeMap = viewService.findClothTypeMap();
        if (clothTypeMap != null) {
            return ResponseBean.success("获取服装类型数据成功", clothTypeMap);
        } else {
            return ResponseBean.error("获取服装类型数据失败", null);
        }
    }

    /**
     * 获取顾客信息
     */
    @GetMapping("/customer")
    public ResponseBean getCustomerSelect(){
        List<Map<Integer,String>> customerMap = viewService.findCustomerMap();
        if(customerMap != null){
            return ResponseBean.success("获取顾客信息成功",customerMap);
        }else {
            return ResponseBean.error("获取顾客信息失败");
        }
    }


    /**
     * 获取量体数据信息
     */
    @GetMapping("/anthropometry/{customerid}")
    public ResponseBean getAnthropometrySelect(@PathVariable("customerid") Integer customerId){
        List<Map<Integer,String>> anthropometryMap = viewService.findAnthropometryMap(customerId);
        if(anthropometryMap != null){
            return ResponseBean.success("获取信息成功",anthropometryMap);
        }else {
            return ResponseBean.error("获取信息失败");
        }
    }

    /**
     * 获取ClothType表的列名和列注释
     */
    @GetMapping("/clothtypecolumn")
    public ResponseBean getClothtypeColumnInfo() {
        List<Map<String, String>> clothtypeColumnInfoList = viewService.findClothtypeColumnInfo();
        ArrayList<Map<String,String>> columnNames = FormatUtils.transforColumnName(clothtypeColumnInfoList);
       /* ArrayList<Map<String, String>> arrayList1 = new ArrayList<Map<String, String>>();

        for (Object obj : clothtypeColumnInfoList) {
            StringBuilder sb = new StringBuilder();
            String columnName = ((Map) obj).get("column_name").toString().toLowerCase(Locale.ROOT);
            String columnComment = ((Map) obj).get("column_comment").toString();
            String[] str = columnName.split("_");
            sb.append(str[0]);
            for (int i = 1; i < str.length; i++) {
                str[i] = str[i].substring(0, 1).toUpperCase() + str[i].substring(1);
                sb.append(str[i]);
            }
            Map<String, String> map = new HashMap();
            map.put("column_name", sb.toString());
            map.put("column_comment", columnComment);
            arrayList1.add(map);

            System.out.println(sb);
        }*/
        if (columnNames != null) {
            return ResponseBean.success("获取信息成功", columnNames);
        } else {
            return ResponseBean.error("获取信息失败");
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
            return ResponseBean.success("获取信息成功", columnNames);
        } else {
            return ResponseBean.error("获取信息失败");
        }
    }

    /**
     * 获取Anthropometric表的列名和列注释
     */
    @GetMapping("/anthropometric")
    public ResponseBean getAnthropometricColumnInfo(){
        List<Map<String, String>> anthropometricColumnInfo = viewService.findAnthropometricColumnInfo();
        ArrayList<Map<String,String>> columnNames = FormatUtils.transforColumnName(anthropometricColumnInfo);

        if (columnNames != null) {
            return ResponseBean.success("获取信息成功", columnNames);
        } else {
            return ResponseBean.error("获取信息失败");
        }
    }

    /**
     * 根据衣服类型获取量体所需数据
     */
    @GetMapping("anthrdata/{clothtype}")
    public ResponseBean getAnthropometricDataByClothType(@PathVariable("clothtype") Integer clothtypeId){
        ClothType clothType = clothTypeService.findClothTypeById(clothtypeId);
        ArrayList<Map<String, String>> columnInfo = FormatUtils.transforColumnName(viewService.findClothtypeColumnInfo());
        ArrayList<Map<String, String>> arrayList =ReflectionUtils.getAnthropometricData(clothType,columnInfo);
        //System.out.println("XXX"+arrayList);
        return ResponseBean.success("获取数据成功",arrayList);
    }

}
