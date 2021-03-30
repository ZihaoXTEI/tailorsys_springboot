package com.xtei.tailorsys.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table clothtype_datainfo
 */

@Data
@ColumnWidth(20)
public class ClothType {
    /**
     * 编号
     * 数据库列名：clothtype_id
     */
    @ColumnWidth(10)
    @ExcelProperty(value = "")
    private Integer clothtypeId;

    /**
     * 服装名称
     * 数据库列名：clothtype_name
     */
    @ExcelProperty(value = "")
    private String clothtypeName;

    /**
     * 衫长
     * 数据库列名：shirt_length
     */
    @ExcelProperty(value = "")
    private Boolean shirtLength;

    /**
     * 胸围
     * 数据库列名：bust
     */
    @ExcelProperty(value = "")
    private Boolean bust;

    /**
     * 总肩宽
     * 数据库列名：shoulder_width
     */
    @ExcelProperty(value = "")
    private Boolean shoulderWidth;

    /**
     * 袖长
     * 数据库列名：sleeve_length
     */
    @ExcelProperty(value = "")
    private Boolean sleeveLength;

    /**
     * 袖口（手腕围）
     * 数据库列名：wrist_circum
     */
    @ExcelProperty(value = "")
    private Boolean wristCircum;

    /**
     * 领围（颈围）
     * 数据库列名：neck_circum
     */
    @ExcelProperty(value = "")
    private Boolean neckCircum;

    /**
     * 裤长
     * 数据库列名：pants_length
     */
    @ExcelProperty(value = "")
    private Boolean pantsLength;

    /**
     * 胸高（乳长）
     * 数据库列名：chest_height
     */
    private Boolean chestHeight;

    /**
     * 裙长
     * 数据库列名：skirt_length
     */
    private Boolean skirtLength;

    /**
     * 腰围
     * 数据库列名：waist_circum
     */
    private Boolean waistCircum;

    /**
     * 臀围
     * 数据库列名：hipline
     */
    private Boolean hipline;

    /**
     * 膝围
     * 数据库列名：knee_circum
     */
    private Boolean kneeCircum;

    /**
     * 踝围（裤脚）
     * 数据库列名：ankle_circum
     */
    private Boolean ankleCircum;

    /**
     * 总长
     * 数据库列名：total_length
     */
    private Boolean totalLength;

    /**
     * 前腰节长
     * 数据库列名：FWL
     */
    private Boolean fwl;

    /**
     * 腰长（背长）
     * 数据库列名：back_length
     */
    private Boolean backLength;

    /**
     * 股臀高
     * 数据库列名：hip_height
     */
    private Boolean hipHeight;

    /**
     * 备注信息
     * 数据库列名：clothtype_note
     */
    private String clothtypeNote;

}