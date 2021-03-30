package com.xtei.tailorsys.util;

import com.xtei.tailorsys.model.Anthropometry;
import com.xtei.tailorsys.model.ClothConsumption;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * FileName: CalculationUntils
 * Author: Li Zihao
 * Date: 2021/3/15 14:28
 * Description:
 */
public class CalculationUntils {

    public static double calculaClothConsumption(Anthropometry anthropometry, ClothConsumption clothConsumption) throws Exception {
        Map<String, Double> anthropometryMap = ReflectionUtils.getFieldAndValue(anthropometry);
        Map<String, Double> clothConsumptionMap = ReflectionUtils.getFieldAndValue(clothConsumption);
        //Set<String> anthropometryKeySet = anthropometryMap.keySet();
        Set<String> clothConsumptionKeySet = clothConsumptionMap.keySet();

/*        System.out.println("============================");
        System.out.println(anthropometryMap);
        System.out.println("============================");
        System.out.println(clothConsumptionMap);*/

        Double totalValue = 0.0;

        Iterator<String> iterator =clothConsumptionKeySet.iterator();
        while(iterator.hasNext()){                         //利用了Iterator迭代器**
            //得到每一个key
            String key = iterator.next();
            if(key.equals("consumWidth") || key.equals("consumNote")){
                continue;
            }
            //通过key获取对应的value
            Double clothConsumptionValue = clothConsumptionMap.get(key);
            Double anthropometryValue = anthropometryMap.get(key);

            totalValue += clothConsumptionValue*anthropometryValue;
        }

        //预留用量
        totalValue += clothConsumptionMap.get("consumNote");

        return totalValue;
    }
}
