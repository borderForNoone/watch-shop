package com.watch.shop.model.enums;

import java.util.Arrays;

public enum WatchType {
    QUARTZ("1"),
    MECHANICAL("2"),
    SOLAR("3"),
    SMART("4");

    private final String code;

    WatchType(String code) {
        this.code = code;
    }

    public static WatchType fromCode(String code) {
        return Arrays.stream(values())
                .filter(t -> t.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Невідомий тип"));
    }

    public String getCode() {
        return code;
    }
}
