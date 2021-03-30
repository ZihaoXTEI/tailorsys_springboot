package com.xtei.tailorsys.model;

import lombok.Data;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table order_fabdet_tb
 */

@Data
public class OrderFabricDetail {
    /**
     * 编号
     * 数据库列名：ofd_no
     */
    private Integer ofdNo;

    /**
     * 订单编号
     * 数据库列名：order_id
     */
    private String orderId;

    /**
     * 布料编号
     * 数据库列名：fabric_id
     */
    private Integer fabricId;

    /**
     * 预算用量
     * 数据库列名：ofd_prede
     */
    private Double ofdPrede;

    /**
     * 实际用量
     * 数据库列名：ofd_usage
     */
    private Double ofdUsage;

}