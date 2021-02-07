package com.etc.domain;

import lombok.Data;

import java.util.List;

@Data
public class PageBean<T> {

    private Integer pageNo;//当前页码
    private Integer pageSize;//每页展示的条数
    private Integer totalCount;//总记录数
    private Integer totalPages;//总页数 需要计算

    private Integer startno;//起始页
    private Integer endno;//结束页

    private List<T> records;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getStartno() {
        return startno;
    }

    public void setStartno(Integer startno) {
        this.startno = startno;
    }

    public Integer getEndno() {
        return endno;
    }

    public void setEndno(Integer endno) {
        this.endno = endno;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public PageBean(Integer pageNo, Integer pageSize, Integer totalCount, List<T> list) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.records = list;

        int x = totalCount % pageSize;
        int y = totalCount / pageSize;
        //如果x=0 商值就是总页数 否则在商值基础上+1
        this.totalPages = (x == 0 ? y : y + 1);

        if (this.totalPages <= 10) {
            this.startno = 1;
            this.endno = this.totalPages;
        } else {
            this.startno = this.pageNo - 4;
            this.endno = this.pageNo + 5;
            if (startno < 1) {
                this.startno = 1;
                this.endno = 10;
            } else if (endno > totalPages) {
                this.endno = totalPages;
                this.startno = totalPages - 9;
            }
        }
    }
}
