package com.xtei.tailorsys.mapper.VO;

import com.xtei.tailorsys.entity.VO.OrderViewVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderViewVOMapper {

    int insert(OrderViewVO record);

    List<OrderViewVO> selectAll(@Param("orderid") String orderid,
                                @Param("customername") String customername,
                                @Param("clothtypeid") Integer clothtypeid,
                                @Param("paymentmethod") Integer paymentmethod,
                                @Param("orderstatus") Integer orderstatus,
                                @Param("screenfield") String screenfield,
                                @Param("earlydate") Date earlydate,
                                @Param("latestdate") Date latestdate,
                                @Param("ordertype") String ordertype);

    List<Map> getOrderQuantityGroupedByClothType();

    List<Map> getOrderQuantityGroupedByCreateTime(String type);

    List<Map> getOrderQuantityGroupedByCreateTimeWithQuarter();

    List<Map> getTurnoverGroupedByCreateTime(String type);

    List<Map> getTurnoverGroupedByCreateTimeWithQuarter();
}