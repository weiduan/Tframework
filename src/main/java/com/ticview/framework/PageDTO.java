package com.ticview.framework;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by weiduan on 2015/10/17 0017.
 */
public class PageDTO<T> implements Serializable {

    private Long totalElement;

    private List<T> list;

    public PageDTO() {

    }

    public PageDTO(Page<T> page) {
        this.totalElement = page.getTotalElements();
        this.list = page.getContent();
    }

    public Long getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(Long totalElement) {
        this.totalElement = totalElement;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
