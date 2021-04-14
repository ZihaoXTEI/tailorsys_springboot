package com.xtei.tailorsys.controller.file;

import com.xtei.tailorsys.entity.response.ResponseBean;
import com.xtei.tailorsys.util.FormatUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * FileName: FileController
 * Author: Li Zihao
 * Date: 2021/3/23 15:34
 * Description: 图片上传、移除控制器
 */
@RestController
@RequestMapping("file")
public class ImageController {

    @PostMapping("/uploadimage")
    public ResponseBean uploadImage(MultipartFile file){
        String fileName = file.getOriginalFilename();
        fileName = FormatUtils.generatedImageName(fileName);

        System.out.println(fileName);

        File dest = new File(fileName);
        try{
            file.transferTo(dest);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("图片上传失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return ResponseBean.success("图片上传成功",fileName);
    }

    @RequestMapping("/removeimage/{imagename}")
    public ResponseBean removeImage(@PathVariable("imagename")String imageName){
        String realPath = "d:/image/";
        System.out.println(imageName);
        File file = new File(realPath + imageName);
        //判断文件是否存在
        if(file.exists()){
            file.delete();
        }else {
            return ResponseBean.error("文件不存在");
        }
        System.out.println("文件删除成功");
        return ResponseBean.success("图片删除成功");
    }

    //@RequestMapping("/{imagename}")
    //public
}
