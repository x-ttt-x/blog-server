package com.example.backend.common.enums.impl;

import com.example.backend.common.enums.IDbEnum;
import com.example.backend.utils.enumConvert;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ECommStatus implements IDbEnum<Integer> {


    /// 未删除
    ACTIVE(0, "ACTIVE"),
    /// 删除
    DELETED(1, "DELETED");


    private final Integer dbValue;
    private final String value;

    @Override
    public Integer getData() {
        return dbValue;
    }

    public static class Converter extends enumConvert<ECommStatus, Integer> {
        Converter() {
            super(ECommStatus.class);
        }
    }

}
