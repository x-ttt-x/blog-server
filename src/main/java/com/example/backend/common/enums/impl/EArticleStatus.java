package com.example.backend.common.enums.impl;

import com.example.backend.common.enums.IDbEnum;
import com.example.backend.utils.enumConvert;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EArticleStatus implements IDbEnum<Integer> {


    /// 下架
    DISABLED(0, "DISABLED"),
    /// 发布
    ENABLED(1, "ENABLED"),
    /// 删除
    DELETED(2, "DELETED");


    private final Integer dbValue;
    private final String value;

    public static boolean isEnumNameExist(String enumName) {
        for (EArticleStatus e : EArticleStatus.values()) {
            if (e.value.equals(enumName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer getData() {
        return dbValue;
    }

    public static class Converter extends enumConvert<EArticleStatus, Integer> {
        Converter() {
            super(EArticleStatus.class);
        }
    }

}
