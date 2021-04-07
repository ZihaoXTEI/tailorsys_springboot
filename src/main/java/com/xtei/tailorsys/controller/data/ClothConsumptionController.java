package com.xtei.tailorsys.controller.data;

import com.github.pagehelper.dialect.ReplaceSql;
import com.xtei.tailorsys.model.Anthropometry;
import com.xtei.tailorsys.model.ClothConsumption;
import com.xtei.tailorsys.model.VO.ClothConsumptionVO;
import com.xtei.tailorsys.model.response.ResponseBean;
import com.xtei.tailorsys.service.AnthropometryService;
import com.xtei.tailorsys.service.DataService;
import com.xtei.tailorsys.service.FabricStockService;
import com.xtei.tailorsys.util.CalculationUntils;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * FileName: ClothQuantityController
 * Author: Li Zihao
 * Date: 2021/3/12 11:19
 * Description:
 */
@RestController
@RequestMapping("data/clothconsumption")
public class ClothConsumptionController {

    @Autowired
    private DataService dataService;
    @Autowired
    private FabricStockService fabricStockService;
    @Autowired
    private AnthropometryService anthropometryService;

    /**
     * 修改服装用料信息
     */
    @PutMapping(value = "/{consumid}")
    public ResponseBean updateClothConsumption(@PathVariable("consumid") Integer consumId, @RequestBody ClothConsumption clothConsumption) {
        ClothConsumption clothConsumptionV = clothConsumption;
        clothConsumptionV.setConsumId(consumId);
        if (dataService.updateClothConsumption(clothConsumptionV) == 1) {
            return ResponseBean.success("修改服装用料信息成功",HttpServletResponse.SC_CREATED);
        } else {
            return ResponseBean.error("修改服装用料信息失败",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新增服装用料信息
     */
    @PostMapping(value = "/")
    public ResponseBean addClothConsumption(@RequestBody ClothConsumption clothConsumption){
        System.out.println(clothConsumption);

        if(dataService.addClothConsumption(clothConsumption) == 1){
            return ResponseBean.success("新增服装用料信息成功",HttpServletResponse.SC_CREATED);
        }else {
            return ResponseBean.error("新增服装用料信息失败",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据编号获取服装用料信息
     */
    @GetMapping(value = "/{consumid}")
    public ResponseBean getClothConsumptionById(@PathVariable("consumid") Integer consumId) {
        ClothConsumption clothConsumption = dataService.findClothConsumptionById(consumId);
        if (clothConsumption != null) {
            return ResponseBean.success("获取服装用料信息成功",HttpServletResponse.SC_PARTIAL_CONTENT, clothConsumption);
        } else {
            return ResponseBean.error("获取服装用料信息失败",HttpServletResponse.SC_NOT_FOUND);
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
            return ResponseBean.success("获取服装用料信息成功",HttpServletResponse.SC_PARTIAL_CONTENT,pageResult);
        }else {
            return ResponseBean.error("获取服装用料信息失败",HttpServletResponse.SC_NOT_FOUND,null);
        }
    }

    /**
     * 计算布料库存量是否满足订单需求
     */
    @GetMapping("verif/{anthtrId}/{clothtypeid}/{fabrics}")
    public ResponseBean verifyFabricIsEnough(@PathVariable("anthtrId") Integer anthrId, @PathVariable("clothtypeid") Integer clothtypeId, @PathVariable("fabrics") Integer[] fabrics) {
        if (anthrId == null || anthrId == 0) {
            return ResponseBean.error("验证数据异常");
        } else if (clothtypeId == null || clothtypeId == 0) {
            return ResponseBean.error("验证数据异常");
        } else if (fabrics == null || fabrics.length == 0) {
            return ResponseBean.error("验证数据异常");
        }

        System.out.println(fabrics);

        double requirement = 0.0;
        ArrayList<String> messageList = new ArrayList<>();
        Anthropometry anthropometry = anthropometryService.findAnthropometryById(anthrId);
        ClothConsumption clothConsumption = dataService.findClothConsumptionById(clothtypeId);

        try {
            requirement = CalculationUntils.calculaClothConsumption(anthropometry, clothConsumption);
            System.out.println("RES" +requirement);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("验证数据异常,算法错误", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        messageList.add(String.valueOf(requirement));

        for (Integer fabricId : fabrics) {
            double v = fabricStockService.getReallyFasStock(fabricId);
            if (v < requirement) {
                messageList.add(String.valueOf(fabricId));
            }
        }

        if (messageList.size() == 1) {
            return ResponseBean.success("所需布料库存充足",messageList);
        } else {
            return ResponseBean.error("所需布料存在不足", messageList);
        }
    }
}
