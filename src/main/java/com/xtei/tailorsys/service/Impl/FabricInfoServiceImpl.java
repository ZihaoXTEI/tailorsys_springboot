package com.xtei.tailorsys.service.Impl;

import com.xtei.tailorsys.mapper.FabricInfoMapper;
import com.xtei.tailorsys.mapper.FabricStockInfoMapper;
import com.xtei.tailorsys.model.FabricInfo;
import com.xtei.tailorsys.model.FabricStockInfo;
import com.xtei.tailorsys.service.FabricInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * FileName: FabricInfoServiceImpl
 * Author: Li Zihao
 * Date: 2021/2/22 15:03
 * Description:
 */

@Service("fabricInfoService")
@Transactional(rollbackFor = Exception.class)
public class FabricInfoServiceImpl implements FabricInfoService {

    @Resource
    private FabricInfoMapper fabricInfoMapper;
    @Resource
    private FabricStockInfoMapper fabricStockInfoMapper;

    @Override
    public int deleteFabricInfo(Integer fabricId) {
        int res = fabricInfoMapper.deleteByPrimaryKey(fabricId);
        return res;
    }

    @Override
    public int addFabricInfo(FabricInfo fabricInfo) {
        FabricInfo fabricInfoV = fabricInfo;
        int res1 = fabricInfoMapper.insert(fabricInfoV);
        FabricStockInfo fabricStockInfo = new FabricStockInfo();
        fabricStockInfo.setFabricId(fabricInfoV.getFabricId());
        fabricStockInfo.setFasStock(0.0);
        fabricStockInfo.setFasPosition("店铺仓库");
        fabricStockInfo.setFasPrede(0.0);
        fabricStockInfo.setUnitPrice(0.0);
        int res2 = fabricStockInfoMapper.insert(fabricStockInfo);

        return res1 + res2;
    }

    @Override
    public FabricInfo findFabricInfoById(Integer fabricId) {
        FabricInfo fabricInfo = fabricInfoMapper.selectByPrimaryKey(fabricId);
        return fabricInfo;
    }

    @Override
    public int updateFabricInfo(FabricInfo fabricInfo) {
        int res = fabricInfoMapper.updateByPrimaryKey(fabricInfo);
        return res;
    }

    @Override
    public List<Map<Integer, String>> findByName(String name) {
        List<Map<Integer, String>> map = fabricInfoMapper.selectByName(name);
        return map;
    }

    @Override
    public int getNumberOfFabricInfo(){
        int res = fabricInfoMapper.getNumberOfFabricInfo();
        return res;
    }
}
