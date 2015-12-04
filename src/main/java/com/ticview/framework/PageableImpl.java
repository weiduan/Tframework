package com.ticview.framework;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * Created by weiduan on 2015/10/17 0017.
 */
public class PageableImpl implements Pageable, Serializable {


    private int page = 1;
    private int size = 10;
    private Sort sort;

    public PageableImpl() {
    }

    public PageableImpl(int page, int size) {
        this(page, size, null);
    }

    public PageableImpl(int page, int size, Sort.Direction direction, String... properties) {
        this(page, size, new Sort(direction, properties));
    }

    public PageableImpl(int page, int size, Sort sort) {
        if (page < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        }

        if (size < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }

        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    @Override
    public int getPageSize() {
        return size;
    }

    @Override
    public int getPageNumber() {
        return page;
    }

    @Override
    public int getOffset() {
        return page * size;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public boolean hasPrevious() {
        return page > 0;
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable next() {
        return new PageableImpl(getPageNumber() + 1, getPageSize(), getSort());
    }

    public Pageable previous() {
        return getPageNumber() == 0 ? this : new PageableImpl(getPageNumber() - 1, getPageSize(), getSort());
    }

    @Override
    public Pageable first() {
        return new PageableImpl(0, getPageSize(), getSort());
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;

        result = prime * result + page;
        result = prime * result + size;

        return prime * result + (null == sort ? 0 : sort.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PageableImpl)) {
            return false;
        }

        PageableImpl that = (PageableImpl) obj;

        boolean sortEqual = this.sort == null ? that.sort == null : this.sort.equals(that.sort);

        return super.equals(that) && sortEqual;
    }

    @Override
    public String toString() {
        return String.format("Page request [number: %d, size %d, sort: %s]", getPageNumber(), getPageSize(),
                sort == null ? null : sort.toString());
    }
}
