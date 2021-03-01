package com.laptrinhjavaweb.dto;

public class PageDTO {
    private Integer page = 1;
    private Integer maxItems = 5;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getMaxItems() {
        return maxItems;
    }

    public void setMaxItems(Integer maxItems) {
        this.maxItems = maxItems;
    }
}
