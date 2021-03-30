package com.xtei.tailorsys.controller.stock;

import com.xtei.tailorsys.model.FabricInfo;
import com.xtei.tailorsys.model.FabricReceiveInfo;
import com.xtei.tailorsys.model.response.ResponseBean;
import com.xtei.tailorsys.service.FabricInfoService;
import com.xtei.tailorsys.service.FabricStockService;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * FileName: FabricController
 * Author: Li Zihao
 * Date: 2021/2/20 20:36
 * Description:布料入库管理页面
 */
@RestController
@RequestMapping("fabricinbound")
public class FabricInboundController {

    @Resource
    private FabricInfoService fabricInfoService;

    @Resource
    private FabricStockService fabricStockService;

    /**
     *新增布料信息
     */
    @PostMapping(value = "/fabricinfo/")
    public ResponseBean addFabricInfo(@RequestBody FabricInfo fabricInfo) {
        System.out.println(fabricInfo);
        if (fabricInfo.getFabricName() == null || fabricInfo.getFabricName().trim() == "") {
            return ResponseBean.error("布料名称不能为空");
        } else if (fabricInfo.getFabricType() == null || fabricInfo.getFabricType() == 0) {
            return ResponseBean.error("布料类型不能为空");
        } else if (fabricInfo.getFabricWidth() == null || fabricInfo.getFabricWidth() <= 0.0) {
            return ResponseBean.error("布料幅度不能为空");
        }

        try {
            if (fabricInfoService.addFabricInfo(fabricInfo) == 2) {
                return ResponseBean.success("新增布料信息成功");
            } else {
                return ResponseBean.error("新增布料信息失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("新增布料信息错误");
        }
    }

    /**
     *修改布料信息
     */
    @PutMapping(value = "/fabricinfo/{fabid}")
    public ResponseBean updateFabricInfo(@PathVariable("fabid") Integer fadId, @RequestBody FabricInfo fabricInfo) {
        FabricInfo fabricInfoV = fabricInfo;
        fabricInfoV.setFabricId(fadId);
        if (fabricInfoService.updateFabricInfo(fabricInfoV) == 1) {
            return ResponseBean.success("修改布料信息成功");
        } else {
            return ResponseBean.error("修改布料信息失败");
        }
    }

    /**
     *根据编号获取布料信息
     */
    @GetMapping("/fabricinfo/{fabid}")
    public ResponseBean getFabricInfoById(@PathVariable("fabid") Integer fabId) {
        FabricInfo fabricInfo = fabricInfoService.findFabricInfoById(fabId);
        if (fabricInfo != null) {
            return ResponseBean.success("获取布料信息成功", fabricInfo);
        } else {
            return ResponseBean.error("获取布料信息失败");
        }
    }

    /**
     *依据输入布料名称搜索符合的布料信息
     */
    @GetMapping("/fabricinfo/{name}")
    public ResponseBean getFabricInfoByName(@PathVariable("name") String name) {
        if (name == null || name.trim() == "") {
            return ResponseBean.error("搜索信息不能为空");
        }
        List<Map<Integer, String>> dropdownList = fabricInfoService.findByName(name.trim());
        return ResponseBean.success("OK", dropdownList);
    }

    /**
     *新增入库信息
     */
    @PostMapping(value = "/fabricrece/")
    public ResponseBean addFabricReceiveInfo(@RequestBody FabricReceiveInfo fabricReceiveInfo) {
        System.out.println(fabricReceiveInfo);
        FabricReceiveInfo fabricReceiveInfoV = fabricReceiveInfo;

        if (fabricReceiveInfoV == null) {
            return ResponseBean.error("新增布料入库信息错误");
        }

        try {
            if (fabricStockService.addFabricReceiveInfo(fabricReceiveInfoV) == 2) {
                return ResponseBean.success("新增布料入库信息成功");
            }
        } catch (Exception e) {
            return ResponseBean.error("新增布料入库信息错误");
        }

        return ResponseBean.error("新增布料入库信息失败");

    }

    /**
     *修改入库信息
     */
    @PutMapping("/fabricrece/{farid}")
    public ResponseBean updateFabricReceiveInfo(@PathVariable("farid") Integer farId, @RequestBody FabricReceiveInfo fabricReceiveInfo) {
        FabricReceiveInfo fabricReceiveInfoV = fabricReceiveInfo;
        fabricReceiveInfoV.setFarId(farId);
        try {
            if (fabricStockService.updateFabricReceiveInfo(fabricReceiveInfoV) == 2) {
                return ResponseBean.success("修改布料入库信息成功");
            }
        } catch (Exception e) {
            return ResponseBean.error("修改布料入库信息错误");
        }
        return ResponseBean.error("修改布料入库信息失败");
    }

    /**
     * 根据编号获取布料入库信息
     */
    @GetMapping("/fabricrece/{farid}")
    public ResponseBean getFabricReceiveById(@PathVariable("farid")Integer farId){
        FabricReceiveInfo fabricReceiveInfo = fabricStockService.findFabricReceiveInfoById(farId);
        if(fabricReceiveInfo != null){
            return ResponseBean.success("获取布料入库信息成功",fabricReceiveInfo);
        }else {
            return ResponseBean.error("获取布料入库信息失败",null);
        }
    }



    /**
     *获取布料入库信息列表
     */
    @GetMapping("/fabricrece/")
    public ResponseBean<PageResult> getFabricReceiveInfoList(@RequestParam(value = "query", defaultValue = "") String query,
                                                             @RequestParam(value = "pagenum", defaultValue = "1") Integer pagenum,
                                                             @RequestParam(value = "pagesize", defaultValue = "15") Integer pagesize) {
        PageResult pageResult = fabricStockService.findFabricReceiveInfoList(query, pagenum, pagesize);
        if (pageResult != null) {
            return ResponseBean.success("获取布料入库信息列表成功", pageResult);
        } else {
            return ResponseBean.error("获取布料入库信息列表失败");
        }
    }
}
