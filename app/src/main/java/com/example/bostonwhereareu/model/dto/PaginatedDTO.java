package com.example.bostonwhereareu.model.dto;

import java.util.List;

public class PaginatedDTO<T> {
    private int totalCount;
    private int offset;
    private int limit;
    private List<T> items;

    public PaginatedDTO() {
    }

    public PaginatedDTO(int totalCount, int offset, int limit, List<T> items) {
        this.totalCount = totalCount;
        this.offset = offset;
        this.limit = limit;
        this.items = items;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public List<T> getItems() {
        return items;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
