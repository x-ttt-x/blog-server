package com.example.backend.common.enums.impl;

import com.example.backend.common.enums.IDbEnum;
import com.example.backend.utils.enumConvert;
import jakarta.persistence.AttributeConverter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EUserStatus implements IDbEnum<Integer> {

    ACTIVE(0, "ACTIVE"),
    LOCKED(1, "LOCKED"),
    DISABLED(2, "DISABLED");

    private final Integer dbValue;
    private final String value;

    @Override
    public Integer getData() {
        return dbValue;
    }

    public static class Converter extends enumConvert<EUserStatus, Integer> {
        Converter() {
            super(EUserStatus.class);
        }
    }
}
