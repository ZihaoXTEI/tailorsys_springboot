package com.xtei.tailorsys.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xtei.tailorsys.mapper.FabricInfoMapper;
import com.xtei.tailorsys.mapper.FabricReceiveInfoMapper;
import com.xtei.tailorsys.mapper.FabricStockInfoMapper;
import com.xtei.tailorsys.mapper.VO.FabricStockInfoVOMapper;
import com.xtei.tailorsys.mapper.FabricUsedInfoMapper;
import com.xtei.tailorsys.mapper.VO.FabricReceiveInfoVOMapper;
import com.xtei.tailorsys.entity.FabricInfo;
import com.xtei.tailorsys.entity.FabricReceiveInfo;
import com.xtei.tailorsys.entity.FabricStockInfo;
import com.xtei.tailorsys.entity.FabricUsedInfo;
import com.xtei.tailorsys.entity.VO.FabricReceiveInfoVO;
import com.xtei.tailorsys.entity.VO.FabricStockInfoVO;
import com.xtei.tailorsys.service.FabricStockService;
import com.xtei.tailorsys.util.FormatUtils;
import com.xtei.tailorsys.util.PageHelperUtils;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * FileName: FabricStockServiceImpl
 * Author: Li Zihao
 * Date: 2021/2/22 16:25
 * Description:
 */

@Service("fabricStockService")
@Transactional(rollbackFor = Exception.class)
public class FabricStockServiceImpl implements FabricStockService {

    @Resource
    private FabricReceiveInfoMapper fabricReceiveInfoMapper;
    @Resource
    private FabricReceiveInfoVOMapper fabricReceiveInfoVOMapper;

    @Resource
    private FabricStockInfoMapper fabricStockInfoMapper;
    @Resource
    private FabricStockInfoVOMapper fabricStockInfoVOMapper;

    @Resource
    private FabricInfoMapper fabricInfoMapper;

    @Resource
    private FabricUsedInfoMapper fabricUsedInfoMapper;

    /*
     *删除布料入库信息
     */
/*    @Override
    public int deleteFabricReceiveInfo(Integer farId){

    }*/

    /*
     *新增布料入库信息
     */
    @Override
    public int addFabricReceiveInfo(FabricReceiveInfo fabricReceiveInfo) {
        double fasStock = 0.0;
        double unitPrice = 0.0;
        int res1 = 0;

        Integer fabricId = fabricReceiveInfo.getFabricId();
        double farLength = fabricReceiveInfo.getFarLength();
        double farPrice = fabricReceiveInfo.getFarPrice();

        if (fabricStockInfoMapper.selectByFaricId(fabricId) == null) {
            //这步可能永远不会执行。。。。。。
            System.out.println("x1");
            FabricStockInfo fabricStockInfo = new FabricStockInfo();
            fabricStockInfo.setFabricId(fabricId);
            fabricStockInfo.setFasStock(fabricReceiveInfo.getFarLength());
            fabricStockInfo.setFasPosition("店铺仓库");
            fabricStockInfo.setFasPrede(0.0);
            fabricStockInfo.setUnitPrice(FormatUtils.MyDecimalFormat(farPrice / farLength));
            System.out.println(fabricStockInfo);
            res1 = fabricStockInfoMapper.insert(fabricStockInfo);
            System.out.println("x2");
        } else {
            System.out.println("x3");
            //获取布料库存中现有库存和单位价格信息
            fasStock = fabricStockInfoMapper.getFasStockByFabricId(fabricId);
            unitPrice = fabricStockInfoMapper.getUnitPriceByFabricId(fabricId);

            fasStock += farLength;
            if (unitPrice == 0.0) {
                unitPrice = FormatUtils.MyDecimalFormat(farPrice / (farLength / 100));
            } else {
                unitPrice = FormatUtils.MyDecimalFormat((farPrice / (farLength / 100) + unitPrice) / 2);
            }

            //更新布料库存信息表
            res1 = fabricStockInfoMapper.updateStockByFabricId(fabricId, fasStock, 0.0, unitPrice);

        }
        System.out.println("x4");

        //更新布料入库信息表
        int res2 = fabricReceiveInfoMapper.insert(fabricReceiveInfo);
        System.out.println("x5");
        return res1 + res2;

    }

    /*
     *修改布料入库信息
     */
    @Override
    public int updateFabricReceiveInfo(FabricReceiveInfo fabricReceiveInfo) {
        double fasStock, unitPrice;
        fasStock = 0;
        unitPrice = 0;

        //获取布料入库信息表的原始数据
        FabricReceiveInfo fabricReceiveInfoO = fabricReceiveInfoMapper.selectByPrimaryKey(fabricReceiveInfo.getFarId());

        System.out.println("原始数据：" + fabricReceiveInfoO);

        //进行数据对比
        double far_length = fabricReceiveInfo.getFarLength() - fabricReceiveInfoO.getFarLength();
        double far_price = fabricReceiveInfo.getFarPrice() - fabricReceiveInfoO.getFarPrice();

        if (far_price + far_length == 0.0) {
            System.out.println("x1");
            int res = fabricReceiveInfoMapper.updateByPrimaryKey(fabricReceiveInfo);
            return res + 1;
        }

        if (far_length != 0.0) {
            System.out.println("x2");
            fasStock = fabricStockInfoMapper.getFasStockByFabricId(fabricReceiveInfoO.getFabricId());
            fasStock += far_length;
            System.out.println("x3");
        }

        if (far_price != 0.0) {
            System.out.println("x4");
            unitPrice = fabricStockInfoMapper.getUnitPriceByFabricId(fabricReceiveInfoO.getFabricId());
            System.out.println("up:" + unitPrice);
            double original = 2 * unitPrice - (fabricReceiveInfoO.getFarPrice() / (fabricReceiveInfoO.getFarLength() / 100));

            unitPrice = ((fabricReceiveInfo.getFarPrice() / (fabricReceiveInfo.getFarLength() / 100)) + original) / 2;
            System.out.println("x5");

            //四舍五入，保留两位小数
            unitPrice = FormatUtils.MyDecimalFormat(unitPrice);
        }

        if (fasStock < 0.0 || unitPrice < 0.0) {
            return -1;
        }
        System.out.println("x6");

        //更新布料库存信息表
        int res1 = fabricStockInfoMapper.updateStockByFabricId(fabricReceiveInfo.getFabricId(), fasStock, 0.0, unitPrice);
        System.out.println("x7");
        //更新布料入库信息表
        int res2 = fabricReceiveInfoMapper.updateByPrimaryKey(fabricReceiveInfo);
        return res1 + res2;

    }

    /*
     *获取布料入库信息表数据
     */
    @Override
    public PageResult findFabricReceiveInfoList(String query, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<FabricReceiveInfoVO> fabricReceiveInfoVOs = fabricReceiveInfoVOMapper.selectAll(query);
        PageInfo<FabricReceiveInfoVO> fabricReceiveInfoVOPageInfo = new PageInfo<FabricReceiveInfoVO>(fabricReceiveInfoVOs);
        return PageHelperUtils.getPageResult(fabricReceiveInfoVOPageInfo);

    }

    /*
     *修改库存信息
     */
    @Override
    public int updateFabricStockInfo(FabricStockInfo fabricStockInfo, FabricInfo fabricInfo) {
        int res1 = fabricInfoMapper.updateByPrimaryKey(fabricInfo);
        int res2 = fabricStockInfoMapper.updateByPrimaryKey(fabricStockInfo);
        return res1 + res2;
    }

    /*
     *获取布料库存信息表数据
     */
    @Override
    public PageResult findFabricStockInfoVOList(String fabricname, Integer fabrictype, String ordertype, String orderfield, Double min, Double max, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<FabricStockInfoVO> fabricStockInfos = fabricStockInfoVOMapper.selectAll(fabricname, fabrictype, ordertype, orderfield, min, max);
        PageInfo<FabricStockInfoVO> fabricStockInfoVOPageInfo = new PageInfo<FabricStockInfoVO>(fabricStockInfos);
        return PageHelperUtils.getPageResult(fabricStockInfoVOPageInfo);

    }

    /**
     * 根据fasId获取布料库存信息
     */
    @Override
    public FabricStockInfoVO findFabricStockInfoVOByFasId(Integer fasId) {
        FabricStockInfoVO fabricStockInfoVO = fabricStockInfoVOMapper.selectByFasId(fasId);
        return fabricStockInfoVO;
    }


    /*
     *新增布料使用信息
     */
    @Override
    public int addFabricUsedInfo(FabricUsedInfo fabricUsedInfo) {
        double fas_prede = fabricStockInfoMapper.getFasPredeByFabricId(fabricUsedInfo.getFabricId());

        fas_prede += fabricUsedInfo.getUsedLength();

        //更新布料库存信息表
        int res1 = fabricStockInfoMapper.updateStockByFabricId(fabricUsedInfo.getFabricId(), 0.0, fas_prede, 0.0);
        //更新布料使用信息表
        int res2 = fabricUsedInfoMapper.insert(fabricUsedInfo);

        return res1 + res2;
    }

    /*
     *删除布料使用信息
     */
    @Override
    public int deleteFabricUsedInfo(Integer fauId) {
        FabricUsedInfo fabricUsedInfo = fabricUsedInfoMapper.selectByPrimaryKey(fauId);
        double used_length = fabricUsedInfo.getUsedLength();

        double fas_prede = fabricStockInfoMapper.getFasPredeByFabricId(fabricUsedInfo.getFabricId());

        fas_prede -= used_length;

        if (fas_prede < 0.0) {
            return -1;
        }
        //更新布料库存信息表
        int res1 = fabricStockInfoMapper.updateStockByFabricId(fabricUsedInfo.getFabricId(), 0.0, fas_prede, 0.0);

        int res2 = fabricReceiveInfoMapper.deleteByPrimaryKey(fauId);
        return res1 + res2;
    }

    /*
     *修改布料使用信息
     */
    @Override
    public int updateFabricUsedInfo(FabricUsedInfo fabricUsedInfo) {
        double used_length = fabricUsedInfoMapper.selectByPrimaryKey(fabricUsedInfo.getFauId()).getUsedLength();
        double fas_prede = fabricStockInfoMapper.getFasPredeByFabricId(fabricUsedInfo.getFabricId());

        used_length = fabricUsedInfo.getUsedLength() - used_length;

        fas_prede += used_length;

        if (fas_prede < 0.0) {
            return -1;
        }
        //更新布料库存信息表
        int res1 = fabricStockInfoMapper.updateStockByFabricId(fabricUsedInfo.getFabricId(), 0.0, fas_prede, 0.0);
        //更新布料使用信息表
        int res2 = fabricUsedInfoMapper.updateByPrimaryKey(fabricUsedInfo);
        return res1 + res2;
    }


    /**
     * 根据Id获取布料入库信息
     */
    public FabricReceiveInfo findFabricReceiveInfoById(Integer farId) {
        FabricReceiveInfo fabricReceiveInfo = fabricReceiveInfoMapper.selectByPrimaryKey(farId);
        return fabricReceiveInfo;
    }

    /**
     * 获取布料库存量
     */
    @Override
    public double getFasStockByFabricId(Integer fabricId) {
        double value = fabricStockInfoMapper.getFasStockByFabricId(fabricId);
        return value;
    }

    /**
     * 获取布料余量（库存量-预定量）
     */
    @Override
    public double getReallyFasStock(Integer fabricId) {
        double value = fabricStockInfoMapper.getFasStockByFabricId(fabricId) - fabricStockInfoMapper.getFasPredeByFabricId(fabricId);
        return value;
    }

    @Override
    public List<Map> getFabricInfoLower() {
        List<Map> dataList = fabricStockInfoMapper.getFabricInfoLower();
        return dataList;
    }

    @Override
    public List<FabricStockInfoVO> getAllFabricStock() {
        List<FabricStockInfoVO> fabricStockInfoVOList = fabricStockInfoVOMapper.selectAll(null, null, null, null, null, null);
        return fabricStockInfoVOList;
    }

    @Override
    public List<FabricReceiveInfoVO> getAllFabricReceive(){
        List<FabricReceiveInfoVO> fabricReceiveInfoVOList = fabricReceiveInfoVOMapper.selectAll(null);
        return fabricReceiveInfoVOList;
    }

}
