package com.javaweb.enums;

import java.util.Map;
import java.util.TreeMap;

public enum TypeCode {
    TANG_TRET("Tầng trệt"),
    NOI_THAT("Nội thất"),
    NGUYEN_CAN("Nguyên căn");

    private final String name;

    TypeCode(String name) {
        this.name = name;
    }

    public static Map<String, String> typeCode() {
        Map<String, String> result = new TreeMap<>();
        for (TypeCode typeCode : TypeCode.values()) {
            result.put(typeCode.toString(), typeCode.name);
        }

        return result;
    }
}
