package com.laptrinhjavaweb.page;

public interface Pageable {
    int getPage();
    int getOffset();
    int getLimit();
}
