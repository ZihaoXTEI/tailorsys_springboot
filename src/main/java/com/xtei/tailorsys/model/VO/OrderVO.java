package com.xtei.tailorsys.model.VO;

import com.xtei.tailorsys.model.OrderFabricDetail;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * FileName: OrderVO
 * Author: Li Zihao
 * Date: 2021/3/16 11:57
 * Description:
 */

@Data
public class OrderVO implements Serializable {

    /**
     * 订单编号
     * 数据库列名：order_id
     */
    private String orderId;

    /**
     * 顾客编号
     * 数据库列名：customer_id
     */
    private Integer customerId;

    /**
     * 顾客实围编号
     * 数据库列名：anthr_id
     */
    private Integer anthrId;

    /**
     * 服装类型编号
     * 数据库列名：clothtype_id
     */
    private Integer clothtypeId;

    /**
     * 订单名称
     * 数据库列名：order_name
     */
    private String orderName;

    /**
     * 订单笔记
     * 数据库列名：order_note
     */
    private String orderNote;

    /**
     * 定做数量
     * 数据库列名：order_number
     */
    private Integer orderNumber;

    /**
     * 总金额
     * 数据库列名：total_amount
     */
    private Double totalAmount;

    /**
     * 支付方式
     * 数据库列名：payment_method
     */
    private Integer paymentMethod;

    /**
     * 预交金额
     * 数据库列名：booked_amount
     */
    private Double bookedAmount;

    /**
     * 订单状态
     * 数据库列名：order_status
     */
    private Integer orderStatus;

    /**
     * 订单创建时间
     * 数据库列名：created_time
     */
    private Date createdTime;

    /**
     * 交货时间
     * 数据库列名：deadline
     */
    private Date deadline;

    /**
     * 所需布料
     */
    private List<OrderFabricDetail> orderFabricDetailList;
}
