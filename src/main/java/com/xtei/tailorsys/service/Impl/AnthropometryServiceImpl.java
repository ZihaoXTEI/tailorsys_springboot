package com.xtei.tailorsys.service.Impl;

import com.xtei.tailorsys.mapper.AnthropometryMapper;
import com.xtei.tailorsys.model.Anthropometry;
import com.xtei.tailorsys.service.AnthropometryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * FileName: AnthropometryServiceImpl
 * Author: Li Zihao
 * Date: 2021/3/27 22:08
 * Description:
 */

@Service("anthropometryService")
@Transactional(rollbackFor = Exception.class)
public class AnthropometryServiceImpl implements AnthropometryService {

    @Resource
    private AnthropometryMapper anthropometryMapper;

    /**
     * 根据编号获取顾客的量体数据
     */
    @Override
    public Anthropometry findAnthropometryById(Integer anthrId) {
        Anthropometry anthropometry = anthropometryMapper.selectByPrimaryKey(anthrId);
        return anthropometry;
    }

    /**
     * 新建顾客量体数据
     */
    @Override
    public int addAnthropometry(Integer customerId, String anthrNote, Date measureTime) {
        Anthropometry anthropometry = new Anthropometry();
        anthropometry.setCustomerId(customerId);
        anthropometry.setAnthrNote(anthrNote);
        anthropometry.setMeasureTime(measureTime);
        anthropometryMapper.insert(anthropometry);
        return anthropometry.getAnthrId();
    }

    /**
     * 更新顾客量体信息
     */
    @Override
    public int updateAnthropometry(Anthropometry anthropometry) {
        int res = anthropometryMapper.updateByPrimaryKey(anthropometry);
        return res;
    }
}
