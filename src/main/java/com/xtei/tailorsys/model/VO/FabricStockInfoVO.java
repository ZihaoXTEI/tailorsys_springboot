package com.xtei.tailorsys.model.VO;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table fabric_stock_view
 */

@Data
@ColumnWidth(20)
public class FabricStockInfoVO {
    /**
     * 编号
     * 数据库列名：fas_id
     */
    @ColumnWidth(10)
    @ExcelProperty(value = "编号",index = 0)
    private Integer fasId;

    /**
     * 布料编号
     * 数据库列名：fabric_id
     */
    @ExcelIgnore
    private Integer fabricId;

    /**
     * 布料名称
     * 数据库列名：fabric_name
     */
    @ColumnWidth(40)
    @ExcelProperty(value = "布料名称",index = 1)
    private String fabricName;

    /**
     * 预定用量
     * 数据库列名：fas_prede
     */
    @ExcelProperty(value = "预定用量",index = 4)
    private Double fasPrede;

    /**
     * 库存位置
     * 数据库列名：fas_position
     */
    @ExcelProperty(value = "库存位置",index = 7)
    private String fasPosition;

    /**
     * 现有库存
     * 数据库列名：fas_stock
     */
    @ExcelProperty(value = "现有库存",index = 5)
    private Double fasStock;

    /**
     * 单位价格
     * 数据库列名：unit_price
     */
    @ExcelProperty(value = "单位价格",index = 6)
    private Double unitPrice;

    /**
     * 布料类型
     * 数据库列名：fabric_type
     */
    @ExcelIgnore
    private Integer fabricType;

    /**
     * 布料类型名称
     * 数据库列名：fabrictype_name
     */
    @ColumnWidth(30)
    @ExcelProperty(value = "布料类型名称",index = 2)
    private String fabrictypeName;

    /**
     * 布料幅宽
     * 数据库列名：fabric_width
     */
    @ExcelProperty(value = "布料幅宽",index = 3)
    private Double fabricWidth;

    /**
     * 布料图片URL
     * 数据库列名：fabric_url
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "图片名称",index = 8)
    private String fabricUrl;

}