package com.xtei.tailorsys.mapper;

import com.xtei.tailorsys.entity.Anthropometry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AnthropometryMapper {
    int deleteByPrimaryKey(Integer anthrId);

    int insert(Anthropometry record);

    Anthropometry selectByPrimaryKey(Integer anthrId);

    List<Anthropometry> selectAll();

    int updateByPrimaryKey(Anthropometry record);

    List<Map<Integer,String>> selectAnthropometryMap (Integer customerId);

    List<Map<String,String>> selectColumnInfo();
}