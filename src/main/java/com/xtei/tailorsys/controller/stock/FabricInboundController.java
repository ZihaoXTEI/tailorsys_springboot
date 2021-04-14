package com.xtei.tailorsys.controller.stock;

import com.xtei.tailorsys.entity.FabricInfo;
import com.xtei.tailorsys.entity.FabricReceiveInfo;
import com.xtei.tailorsys.entity.response.ResponseBean;
import com.xtei.tailorsys.service.FabricInfoService;
import com.xtei.tailorsys.service.FabricStockService;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * FileName: FabricController
 * Author: Li Zihao
 * Date: 2021/2/20 20:36
 * Description: 布料入库管理页面视图控制器
 */

@RestController
@RequestMapping("fabricinbound")
public class FabricInboundController {

    @Autowired
    private FabricInfoService fabricInfoService;

    @Autowired
    private FabricStockService fabricStockService;

    /**
     * 新增布料信息
     */
    @PostMapping(value = "/fabricinfo/")
    public ResponseBean addFabricInfo(@RequestBody FabricInfo fabricInfo) {
        if (fabricInfo.getFabricName() == null || fabricInfo.getFabricName().trim() == "") {
            return ResponseBean.error("布料名称不能为空");
        } else if (fabricInfo.getFabricType() == null || fabricInfo.getFabricType() == 0) {
            return ResponseBean.error("布料类型不能为空");
        } else if (fabricInfo.getFabricWidth() == null || fabricInfo.getFabricWidth() <= 0.0) {
            return ResponseBean.error("布料幅度不能为空");
        }

        try {
            fabricInfoService.addFabricInfo(fabricInfo);
            return ResponseBean.success("新增布料信息成功", HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("新增布料信息错误", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 修改布料信息
     */
    @PutMapping(value = "/fabricinfo/{fabid}")
    public ResponseBean updateFabricInfo(@PathVariable("fabid") Integer fadId, @RequestBody FabricInfo fabricInfo) {
        FabricInfo fabricInfoV = fabricInfo;
        fabricInfoV.setFabricId(fadId);
        try {
            fabricInfoService.updateFabricInfo(fabricInfoV);
            return ResponseBean.success("修改布料信息成功", HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("修改布料信息失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据编号获取布料信息
     */
    @GetMapping("/fabricinfo/{fabid}")
    public ResponseBean getFabricInfoById(@PathVariable("fabid") Integer fabId) {
        try {
            FabricInfo fabricInfo = fabricInfoService.findFabricInfoById(fabId);
            if (fabricInfo != null) {
                return ResponseBean.success("获取布料信息成功", HttpServletResponse.SC_PARTIAL_CONTENT, fabricInfo);
            } else {
                return ResponseBean.error("获取布料信息为空", HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("获取布料信息失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 依据输入布料名称搜索符合的布料信息
     */
    @GetMapping("/fabricinfo/{name}")
    public ResponseBean getFabricInfoByName(@PathVariable("name") String name) {
        if (name == null || name.trim() == "") {
            return ResponseBean.error("搜索信息不能为空");
        }
        try {
            List<Map<Integer, String>> dropdownList = fabricInfoService.findByName(name.trim());
            return ResponseBean.success("OK", HttpServletResponse.SC_PARTIAL_CONTENT, dropdownList);
        } catch (Exception e) {
            return ResponseBean.error("ERROR", HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
        }
    }

    /**
     * 新增入库信息
     */
    @PostMapping(value = "/fabricrece/")
    public ResponseBean addFabricReceiveInfo(@RequestBody FabricReceiveInfo fabricReceiveInfo) {

        FabricReceiveInfo fabricReceiveInfoV = fabricReceiveInfo;

        if (fabricReceiveInfoV == null) {
            return ResponseBean.error("传入的数据为空", HttpServletResponse.SC_BAD_REQUEST);
        }

        try {
            fabricStockService.addFabricReceiveInfo(fabricReceiveInfoV);
            return ResponseBean.success("新增布料入库信息成功", HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            return ResponseBean.error("新增布料入库信息错误", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 修改入库信息
     */
    @PutMapping("/fabricrece/{farid}")
    public ResponseBean updateFabricReceiveInfo(@PathVariable("farid") Integer farId, @RequestBody FabricReceiveInfo fabricReceiveInfo) {
        FabricReceiveInfo fabricReceiveInfoV = fabricReceiveInfo;
        fabricReceiveInfoV.setFarId(farId);
        try {
            fabricStockService.updateFabricReceiveInfo(fabricReceiveInfoV);
            return ResponseBean.success("修改布料入库信息成功", HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            return ResponseBean.error("修改布料入库信息错误", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据编号获取布料入库信息
     */
    @GetMapping("/fabricrece/{farid}")
    public ResponseBean getFabricReceiveById(@PathVariable("farid") Integer farId) {
        try {
            FabricReceiveInfo fabricReceiveInfo = fabricStockService.findFabricReceiveInfoById(farId);
            if (fabricReceiveInfo != null) {
                return ResponseBean.success("获取布料入库信息成功", HttpServletResponse.SC_PARTIAL_CONTENT, fabricReceiveInfo);
            } else {
                return ResponseBean.error("获取布料入库信息为空", HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("获取布料入库信息失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 获取布料入库信息列表
     */
    @GetMapping("/fabricrece/")
    public ResponseBean<PageResult> getFabricReceiveInfoList(@RequestParam(value = "query", defaultValue = "") String query,
                                                             @RequestParam(value = "pagenum", defaultValue = "1") Integer pagenum,
                                                             @RequestParam(value = "pagesize", defaultValue = "15") Integer pagesize) {
        try {
            PageResult pageResult = fabricStockService.findFabricReceiveInfoList(query, pagenum, pagesize);
            if (pageResult != null) {
                return ResponseBean.success("获取布料入库信息列表成功", HttpServletResponse.SC_OK, pageResult);
            } else {
                return ResponseBean.error("获取布料入库信息列表为空", HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("获取布料入库信息列表错误", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
