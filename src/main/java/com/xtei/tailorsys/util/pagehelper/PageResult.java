package com.xtei.tailorsys.util.pagehelper;

import lombok.Data;

import java.util.List;

/**
 * FileName: PageResult
 * Author: Li Zihao
 * Date: 2021/3/4 14:09
 * Description:
 */

@Data
public class PageResult {

    //当前页码
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;
    /**
     * 记录总数
     */
    private long totalSize;
    /**
     * 页码总数
     */
    private int totalPages;
    /**
     * 数据模型
     */
    private List<?> content;

}
