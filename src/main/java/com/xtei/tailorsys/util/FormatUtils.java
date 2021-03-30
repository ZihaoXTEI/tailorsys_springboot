package com.xtei.tailorsys.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * FileName: FormatUtils
 * Author: Li Zihao
 * Date: 2021/2/23 20:16
 * Description: 数据格式化工具
 */
public class FormatUtils {

    //保留两位小数
    public static double MyDecimalFormat(double number){
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return Double.parseDouble(decimalFormat.format(number));
    }

    //生成订单编号
    public static String generatedOrderId(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        Random random =new Random();
        int ran = random.nextInt(10000);

        Date currentDate = new Date();
        String orderId = dateFormat.format(currentDate)+ String.valueOf(ran);

        return orderId;
    }

    //转换表格名称：user_Id =>userId
    public static ArrayList<Map<String, String>> transforColumnName(List<Map<String,String>> columnNameList){
        ArrayList<Map<String,String>> columnNames = new ArrayList<Map<String,String>>();
        for (Object obj : columnNameList) {
            StringBuilder sb = new StringBuilder();
            String columnName = ((Map) obj).get("column_name").toString().toLowerCase(Locale.ROOT);
            String columnComment = ((Map) obj).get("column_comment").toString();
            String[] str = columnName.split("_");
            sb.append(str[0]);
            for (int i = 1; i < str.length; i++) {
                str[i] = str[i].substring(0, 1).toUpperCase() + str[i].substring(1);
                sb.append(str[i]);
            }

            //System.out.println(sb);

            Map<String, String> map = new HashMap();
            map.put("column_name", sb.toString());
            map.put("column_comment", columnComment);
            columnNames.add(map);
        }
        return columnNames;
    }

    //传入表名，查找出备注
    public static String findColumnComment(String columnName,ArrayList<Map<String, String>> columnInfo){
        Iterator<Map<String, String>> iterator = columnInfo.iterator();
        while (iterator.hasNext()){
            Map<String,String> map = iterator.next();
            if(map.get("column_name").equals(columnName)){
                return map.get("column_comment");
            }
        }
        return "";
    }

    //生成上传图片名称
    public static String generatedImageName(String fileName){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        Date currentDate = new Date();
        String imageName = dateFormat.format(currentDate)+'-'+fileName;

        return imageName;
    }

    //MapList => List(废除方法）
    public List MapListToArray(List<Map<String ,Double>> dataList){
        ArrayList arr = new ArrayList();
        for(Object o : dataList){
            Map mapInfo = (Map) o;
            Set<String> keySet = mapInfo.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
            }
        }
        return null;
    }

}
