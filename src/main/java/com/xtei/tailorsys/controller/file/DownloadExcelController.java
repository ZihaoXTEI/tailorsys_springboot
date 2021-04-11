package com.xtei.tailorsys.controller.file;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.xtei.tailorsys.entity.Customer;
import com.xtei.tailorsys.entity.OrderProcess;
import com.xtei.tailorsys.entity.Supplier;
import com.xtei.tailorsys.entity.VO.FabricReceiveInfoVO;
import com.xtei.tailorsys.entity.VO.FabricStockInfoVO;
import com.xtei.tailorsys.entity.VO.OrderViewVO;
import com.xtei.tailorsys.service.CustomerService;
import com.xtei.tailorsys.service.FabricStockService;
import com.xtei.tailorsys.service.OrderService;
import com.xtei.tailorsys.service.SupplierService;
import com.xtei.tailorsys.util.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: DownloadExcelController
 * Author: Li Zihao
 * Date: 2021/3/30 19:20
 * Description:
 */
@RestController
@RequestMapping("system/excel")
public class DownloadExcelController {

    @Resource
    private CustomerService customerService;
    @Resource
    private OrderService orderService;
    @Resource
    private FabricStockService fabricStockService;
    @Resource
    private SupplierService supplierService;

    @GetMapping("/customer")
    public void downloadCustomerInfo(HttpServletResponse response) throws IOException {
        try {
            List<Customer> customerList = customerService.getAllCustomer();
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // URLEncoder.encode可以防止中文乱码
            String fileName = URLEncoder.encode("顾客信息", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), Customer.class).autoCloseStream(Boolean.FALSE).sheet("参数").doWrite(customerList);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map map = new HashMap();
            map.put("status", HttpStatus.SERIOUS_ERROR);
            map.put("message", "下载文件失败");
            response.getWriter().println(JSON.toJSONString(map));
        }
    }

    @GetMapping("/order")
    public void downloadOrderInfo(HttpServletResponse response) throws IOException{
        try {
            List<OrderViewVO> orderViewVOList = orderService.getAllOrder();
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // URLEncoder.encode可以防止中文乱码
            String fileName = URLEncoder.encode("订单信息", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), OrderViewVO.class).autoCloseStream(Boolean.FALSE).sheet("参数").doWrite(orderViewVOList);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map map = new HashMap();
            map.put("status", HttpStatus.SERIOUS_ERROR);
            map.put("message", "下载文件失败");
            response.getWriter().println(JSON.toJSONString(map));
        }
    }

    @GetMapping("/orderprocess")
    public void downloadOrderProcessInfo(HttpServletResponse response) throws IOException{
        try {
            List<OrderProcess> orderProcessList = orderService.getAllOrderProcess();
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // URLEncoder.encode可以防止中文乱码
            String fileName = URLEncoder.encode("订单流程信息", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), OrderProcess.class).autoCloseStream(Boolean.FALSE).sheet("参数").doWrite(orderProcessList);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map map = new HashMap();
            map.put("status", HttpStatus.SERIOUS_ERROR);
            map.put("message", "下载文件失败");
            response.getWriter().println(JSON.toJSON(map));
        }
    }

    @GetMapping("/fabricstock")
    public void downloadFabricStockInfo(HttpServletResponse response) throws IOException {
        try {
            List<FabricStockInfoVO> fabricStockInfoVOList = fabricStockService.getAllFabricStock();
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // URLEncoder.encode可以防止中文乱码
            String fileName = URLEncoder.encode("布料库存信息", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), FabricStockInfoVO.class).autoCloseStream(Boolean.FALSE).sheet("参数").doWrite(fabricStockInfoVOList);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map map = new HashMap();
            map.put("status", HttpStatus.SERIOUS_ERROR);
            map.put("message", "下载文件失败");
            response.getWriter().println(JSON.toJSON(map));
/*            PrintWriter out = response.getWriter();
            out.write("{\"status\":400,\"message\":\"下载文件失败\"}");
            out.flush();
            out.close();*/

        }
    }

    @GetMapping("/fabricreceive")
    public void downloadFabricReceiveInfo(HttpServletResponse response) throws IOException{
        try {
            List<FabricReceiveInfoVO> fabricReceiveInfoVOList = fabricStockService.getAllFabricReceive();
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // URLEncoder.encode可以防止中文乱码
            String fileName = URLEncoder.encode("布料入库信息", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), FabricReceiveInfoVO.class).autoCloseStream(Boolean.FALSE).sheet("参数").doWrite(fabricReceiveInfoVOList);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map map = new HashMap();
            map.put("status", HttpStatus.SERIOUS_ERROR);
            map.put("message", "下载文件失败");
            response.getWriter().println(JSON.toJSON(map));
        }
    }

    @GetMapping("/supplier")
    public void downloadSupplierInfo(HttpServletResponse response) throws IOException{
        try {
            List<Supplier> supplierList = supplierService.getAllSupplier();
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // URLEncoder.encode可以防止中文乱码
            String fileName = URLEncoder.encode("布料入库信息", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), Supplier.class).autoCloseStream(Boolean.FALSE).sheet("参数").doWrite(supplierList);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map map = new HashMap();
            map.put("status", HttpStatus.SERIOUS_ERROR);
            map.put("message", "下载文件失败");
            response.getWriter().println(JSON.toJSON(map));
        }
    }

}
