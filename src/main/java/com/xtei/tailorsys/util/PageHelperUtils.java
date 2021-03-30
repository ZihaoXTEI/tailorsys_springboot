package com.xtei.tailorsys.util;

import com.github.pagehelper.PageInfo;
import com.xtei.tailorsys.util.pagehelper.PageResult;

/**
 * FileName: PageUtils
 * Author: Li Zihao
 * Date: 2021/3/4 14:06
 * Description:
 */
public class PageHelperUtils {
    /**
     * 将分页信息封装到统一的接口
     * @return
     */
    public static PageResult getPageResult(PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }

}
