package com.xtei.tailorsys.controller.stock;

import com.xtei.tailorsys.entity.Supplier;
import com.xtei.tailorsys.entity.response.ResponseBean;
import com.xtei.tailorsys.service.SupplierService;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * FileName: SupplierController
 * Author: Li Zihao
 * Date: 2021/2/20 20:35
 * Description:
 */

@RestController
@RequestMapping("supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    /**
     * 增加供应商信息
     */
    @PostMapping(value = "/", produces = "application/json;charset=UTF-8")
    public ResponseBean addSupplier(@RequestBody Supplier supplier) {
        System.out.println(supplier);
        if (supplier.getSupName() == null || supplier.getSupName().trim() == "") {
            return ResponseBean.error("供应商名称不能为空", HttpServletResponse.SC_BAD_REQUEST);
        }

        try {
            supplierService.addSupplier(supplier);
            return ResponseBean.success("新增供应商信息成功", HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("新增供应商信息失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * 修改供应商信息
     */
    @PutMapping(value = "/{supid}")
    public ResponseBean updateSupplier(@PathVariable("supid") Integer supId, @RequestBody Supplier supplier) {
        Supplier supplierV = supplier;
        supplierV.setSupId(supId);
        try {
            supplierService.updateSupplierInfo(supplierV);
            return ResponseBean.success("修改供应商信息成功",HttpServletResponse.SC_CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("修改供应商信息失败",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 修改供应商状态
     */
    @PutMapping(value = "/{supid}/{status}")
    public ResponseBean updateSupplierStatus(@PathVariable("supid") Integer supId, @PathVariable("status") boolean supStatus) {
        try {
            supplierService.updateSupplierStatus(supId, supStatus);
            return ResponseBean.success("修改供应商状态成功",HttpServletResponse.SC_CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("修改供应商状态失败",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据编号获取供应商信息
     */
    @GetMapping(value = "/{supid}")
    public ResponseBean getSupplierById(@PathVariable("supid") Integer supId) {
        try {
            Supplier supplier = supplierService.findSupplierById(supId);
            if (supplier != null) {
                return ResponseBean.success("获取供应商信息成功",HttpServletResponse.SC_PARTIAL_CONTENT, supplier);
            } else {
                return ResponseBean.error("获取供应商信息为空",HttpServletResponse.SC_NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("获取供应商信息失败",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取供应商数据列表
     */
    @GetMapping(value = "/")
    public ResponseBean<PageResult> getSupplierList(@RequestParam(value = "query", defaultValue = "") String query,
                                                    @RequestParam(value = "pagenum", defaultValue = "1") Integer pagenum,
                                                    @RequestParam(value = "pagesize", defaultValue = "10") Integer pagesize) {
        try {
            PageResult pageResult = supplierService.findSupplierList(query, pagenum, pagesize);
            if (pageResult != null) {
                return ResponseBean.success("获取供应商列表成功",HttpServletResponse.SC_OK, pageResult);
            } else {
                return ResponseBean.error("获取供应商列表为空",HttpServletResponse.SC_NOT_FOUND, null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("获取供应商列表失败",HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
        }
    }


}
