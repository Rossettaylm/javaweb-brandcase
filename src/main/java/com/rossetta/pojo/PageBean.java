package com.rossetta.pojo;

import java.util.List;

/**
 * Created by Rossetta on 2022/3/20
 */
public class PageBean<T> {
    // 总数据条数
    private int totalSize;

    // 一页中显示的数据
    private List<T> rows;

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalSize=" + totalSize +
                ", rows=" + rows +
                '}';
    }
}
