package com.example.backend.common.enums.impl;

import com.example.backend.common.enums.IDbEnum;
import com.example.backend.utils.enumConvert;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EUserType implements IDbEnum<Integer> {
    SUPER_ADMIN(3, "SUPER_ADMIN"),//超级账户
    COMMON_ADMIN(2, "COMMON_ADMIN"),//普通账号
    VIEWER_ADMIN(1, "VIEWER_ADMIN"),//游客后台账号
    COMMON_USER(0, "COMMON_USER");//普通账号

    private final Integer dbValue;
    private final String value;

    @Override
    public Integer getData() {
        return dbValue;
    }

    public static class Converter extends enumConvert<EUserType, Integer> {
        Converter() {
            super(EUserType.class);
        }
    }
}
