package com.xtei.tailorsys.converter;

import com.xtei.tailorsys.entity.FabricInfo;
import com.xtei.tailorsys.entity.FabricStockInfo;
import com.xtei.tailorsys.entity.VO.FabricStockInfoVO;
import org.springframework.beans.BeanUtils;

/**
 * FileName: FabricStockInfoConverter
 * Author: Li Zihao
 * Date: 2021/3/25 18:00
 * Description:
 */
public class FabricStockInfoConverter {

    public static FabricStockInfo converterToFabricStockInfo(FabricStockInfoVO fabricStockInfoVO){
        FabricStockInfo fabricStockInfo =new FabricStockInfo();
        BeanUtils.copyProperties(fabricStockInfoVO,fabricStockInfo);
        return fabricStockInfo;
    }

    public static FabricInfo converterToFabricInfo(FabricStockInfoVO fabricStockInfoVO){
        FabricInfo fabricInfo = new FabricInfo();
        BeanUtils.copyProperties(fabricStockInfoVO,fabricInfo);
        return fabricInfo;
    }
}
