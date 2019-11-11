package com.java.domain;

import lombok.Data;

import java.util.List;

/**
 * @Author : Haotian
 */
@Data
public class PageBean<T> {
    /***
     * description: 分页数据对象
     *
     * @param totalCount 总记录数
     * @param totalPage 总页码
     * @param list 每页的数据存放在一个List集合
     * @param currentPage 当前页码
     * @param rows 每页显示的记录数
     */
    private int totalCount;
    private int totalPage;
    private List<T> list;
    private int currentPage;
    private int rows;
}
