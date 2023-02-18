package com.nowcoder.community.entity;


/**
 * 封装分页相关的信息
 * <p>
 * 接收页面传入信息
 */

public class Page {


    //当前页码

    private int current = 1;
    //上限
    private int limit = 10;
    //数据总数 用于计算总页数
    private int rows;
    //查询路径 用于复用查询分页链接
    private String path;


    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if (current >= 1) this.current = current;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit >= 1 && limit <= 100) this.limit = limit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows >= 0) this.rows = rows;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取当前页的起始行
     *
     * @return
     */
    public int getOffset() {
        return (current - 1) * limit;
    }

    /**
     * 获取总页数
     *
     * @return
     */
    public int getTotal() {
        //行数是整数倍页面行数时
        if ((rows % limit) == 0) {
            return rows / limit;
        } else {

            return rows / limit + 1;
        }
    }


    /**
     * 获取起始页码
     *
     * @return
     */
    public int getFrom() {
        return Math.max(1, current - 2);
    }

    /**
     * 获取结束页码
     *
     * @return
     */


    public int getTo() {
        return Math.min(getTotal(), current + 2);
    }

}
