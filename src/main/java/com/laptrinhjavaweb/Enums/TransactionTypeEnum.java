package com.laptrinhjavaweb.Enums;

import java.util.stream.Stream;

public enum TransactionTypeEnum {
    CUSTOMER_SERVICE("Customer Service Process"),
    HOUSE_VIEWING("House Viewing Process"),
    PHONG("Phong");
    private String fullName;

    TransactionTypeEnum(String fullName) {
        this.fullName = fullName;
    }
    public static Stream<TransactionTypeEnum> stream() {
        return Stream.of(TransactionTypeEnum.values());
    }

    public String getFullName() {
        return fullName;
    }
}
