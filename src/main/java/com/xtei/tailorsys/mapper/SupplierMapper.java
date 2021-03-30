package com.xtei.tailorsys.mapper;

import com.xtei.tailorsys.model.Supplier;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SupplierMapper {
    int deleteByPrimaryKey(Integer supId);

    int insert(Supplier record);

    Supplier selectByPrimaryKey(Integer supId);

    List<Supplier> selectAll(String query);

    int updateByPrimaryKey(Supplier record);

    List<Map<Integer,String>> selectSupplierMap();

    int updateSupplierStatus(Integer supId,boolean supStatus);
}