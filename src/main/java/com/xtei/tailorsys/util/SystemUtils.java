package com.xtei.tailorsys.util;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

/**
 * FileName: SystemUtils
 * Author: Li Zihao
 * Date: 2021/4/16 19:55
 * Description:
 */

public class SystemUtils {
    private static String os= System.getProperty("os.name").toLowerCase();

    public static String filePath(){
        System.out.println(os);
        if(os.startsWith("win")){
            return "d:/image/";
        }else if(os.startsWith("lin")){
            return "/usr/local/tailorsys/static/image";
        }else {
            return null;
        }
    }

}
