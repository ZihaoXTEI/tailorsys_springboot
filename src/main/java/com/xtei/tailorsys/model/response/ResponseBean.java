package com.xtei.tailorsys.model.response;

import com.xtei.tailorsys.util.pagehelper.PageResult;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * FileName: ResponseBean
 * Author: Li Zihao
 * Date: 2021/3/4 10:03
 * Description:
 */

@Data
public class ResponseBean<T> {

    /** 200:操作成功  400：操作失败**/

    // http 状态码
    private int status;

    // 返回信息内容
    private String message;

    // 返回的数据
    private T data;

    public static <T> ResponseBean<T> success(String message,T data) {
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setStatus(200);
        responseBean.setMessage(message);
        responseBean.setData(data);
        return responseBean;
    }


    public static <T> ResponseBean<T> error(String message,T errorData) {
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setStatus(400);
        responseBean.setMessage(message);
        responseBean.setData(errorData);
        return responseBean;
    }

    public static <T> ResponseBean<T> error(String message) {
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setStatus(400);
        responseBean.setMessage(message);
        return responseBean;
    }

    public static <T> ResponseBean<T> success(String message) {
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setStatus(200);
        responseBean.setMessage(message);
        return responseBean;
    }

    /**
     * 自定义返回状态码
     */
    public static <T> ResponseBean<T> success(String message,int status){
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setStatus(status);
        responseBean.setMessage(message);
        return responseBean;
    }

    public static <T> ResponseBean<T> success(String message,int status,T data) {
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setStatus(status);
        responseBean.setMessage(message);
        responseBean.setData(data);
        return responseBean;
    }

    public static <T> ResponseBean<T> error(String message,int status){
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setStatus(status);
        responseBean.setMessage(message);
        return responseBean;
    }

    public static <T> ResponseBean<T> error(String message,int status,T data){
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setStatus(status);
        responseBean.setMessage(message);
        responseBean.setData(data);
        return responseBean;
    }

}
