package com.xtei.tailorsys.controller.data;

import com.xtei.tailorsys.entity.Anthropometry;
import com.xtei.tailorsys.entity.response.ResponseBean;
import com.xtei.tailorsys.service.AnthropometryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * FileName: AnthropometryController
 * Author: Li Zihao
 * Date: 2021/3/7 21:38
 * Description: 量体数据视图控制器
 */
@RestController
@RequestMapping("anthr")
public class AnthropometryController {

    @Autowired
    private AnthropometryService anthropometryService;

    /**
     * 根据编号获取顾客量体信息
     */
    @GetMapping(value = "/{anthrid}")
    public ResponseBean getAnthropometryById(@PathVariable("anthrid") Integer anthrId) {
        try {
            Anthropometry anthropometry = anthropometryService.findAnthropometryById(anthrId);
            if(anthropometry != null){
                return ResponseBean.success("获取顾客量体信息成功", HttpServletResponse.SC_OK, anthropometry);
            }else {
                return ResponseBean.error("获取顾客量体信息为空", HttpServletResponse.SC_NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("获取顾客量体信息错误", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新建顾客的量体数据
     */
    @PostMapping(value = "/{cusid}/{anthrnote}")
    public ResponseBean addAnthropometry(@PathVariable("cusid") Integer customerId, @PathVariable("anthrnote") String anthrNote) {
        try {
            Date currentTime = new Date();
            int res = anthropometryService.addAnthropometry(customerId, anthrNote, currentTime);
            return ResponseBean.success("新建顾客量体信息成功", HttpServletResponse.SC_CREATED, res);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("新建顾客量体信息错误", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 修改顾客量体信息
     */
    @PutMapping("/{anthrid}")
    public ResponseBean updateAnthropometry(@PathVariable("anthrid") Integer anthrId, @RequestBody Anthropometry anthropometry) {
        Anthropometry anthropometryV = anthropometry;
        anthropometryV.setAnthrId(anthrId);
        try {
            Date currentTime = new Date();
            anthropometryV.setMeasureTime(currentTime);
            anthropometryService.updateAnthropometry(anthropometryV);
            return ResponseBean.success("保存顾客量体信息成功", HttpServletResponse.SC_CREATED);
        }catch (Exception e){
            return ResponseBean.error("保存顾客量体信息失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
