package com.xtei.tailorsys.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * FileName: ReflectionUtils
 * Author: Li Zihao
 * Date: 2021/3/13 21:27
 * Description: 反射工具类
 */
public class ReflectionUtils {

    /**
     * 根据属性名获取属性值
     */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取属性类型、属性名、属性值
     */
    public static List getFiledsInfo(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        List list = new ArrayList();
        for (int i = 0; i < fields.length; i++) {
            Map infoMap = new HashMap();
            infoMap.put("type", fields[i].getType().toString());
            infoMap.put("name", fields[i].getName());
            infoMap.put("value", getFieldValueByName(fields[i].getName(),o));
            list.add(infoMap);
        }
        //System.out.println(list.toString());
        return list;
    }

    /**
     * 判断所需量体数据
     */
    public static ArrayList<Map<String,String>> getAnthropometricData(Object o,ArrayList<Map<String, String>> columninfo){
        Field[] fields = o.getClass().getDeclaredFields();
        ArrayList<Map<String,String>> columnInfo = columninfo;
        ArrayList<Map<String,String>> anthrData=new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            if(fields[i].getType().toString().equals("class java.lang.Boolean") && (Boolean)(getFieldValueByName(fields[i].getName(),o)) == true){
                String columnComment = FormatUtils.findColumnComment(fields[i].getName(),columnInfo);
                Map infoMap = new HashMap();
                infoMap.put("column_name",fields[i].getName());
                infoMap.put("column_comment",columnComment);
                anthrData.add(infoMap);
            }
        }
        //System.out.println(anthrData);
        return anthrData;
    }

    /**
     *获得对象中所有Double类型的属性
     */
    public static Map<String, Double> getFieldAndValue(Object o)throws Exception{
        Field[] fields = o.getClass().getDeclaredFields();
        //ArrayList<Map<String, Double>> fieldList=new ArrayList<>();
        Map fieldMap =new HashMap();
        for(int i =0;i<fields.length;i++){
            if(fields[i].getType().toString().equals("class java.lang.Double")){

                fieldMap.put(fields[i].getName(),(Double)(getFieldValueByName(fields[i].getName(),o)));
                //fieldList.add(infoMap);
            }
        }
        return fieldMap;
    }


}
