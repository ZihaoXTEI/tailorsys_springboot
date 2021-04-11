package com.xtei.tailorsys.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.util.Date;

@Data
@ColumnWidth(20)
public class OrderProcess {
    /**
     * 编号
     * 数据库列名：opr_id
     */
    @ExcelIgnore
    private Integer oprId;

    /**
     * 订单编号
     * 数据库列名：order_id
     */
    @ColumnWidth(25)
    @ExcelProperty(value = "订单编号")
    private String orderId;

    /**
     * 裁缝人
     * 数据库列名：tailoring
     */
    @ExcelProperty(value = "裁缝人")
    private String tailoring;

    /**
     * 裁缝日期
     * 数据库列名：tailoring_date
     */
    @ColumnWidth(30)
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty(value = "裁缝日期")
    private Date tailoringDate;

    /**
     * 缝制人
     * 数据库列名：sewing
     */
    @ExcelProperty(value = "缝制人")
    private String sewing;

    /**
     * 缝制日期
     * 数据库列名：sewing_date
     */
    @ColumnWidth(30)
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty(value = "缝制日期")
    private Date sewingDate;

    /**
     * 整烫人
     * 数据库列名：ironing
     */
    @ExcelProperty(value = "整烫人")
    private String ironing;

    /**
     * 整烫日期
     * 数据库列名：ironing_date
     */
    @ColumnWidth(30)
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty(value = "整烫日期")
    private Date ironingDate;

    /**
     * 完成人
     * 数据库列名：finish
     */
    @ExcelProperty(value = "完成人")
    private String finish;

    /**
     * 完成日期
     * 数据库列名：finish_date
     */
    @ColumnWidth(30)
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty(value = "完成日期")
    private Date finishDate;

}