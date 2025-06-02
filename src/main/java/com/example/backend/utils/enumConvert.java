package com.example.backend.utils;

import com.example.backend.common.enums.IDbEnum;
import jakarta.persistence.AttributeConverter;


public abstract class enumConvert<ATTR extends Enum<ATTR> & IDbEnum<DB>,DB> implements AttributeConverter<ATTR,DB> {

    private final Class<ATTR> dbType;

    public enumConvert(Class<ATTR> dbType) {
        this.dbType = dbType;
    }

    @Override
    public DB convertToDatabaseColumn(ATTR attribute) {
        return attribute != null ? attribute.getData() : null;
    }

    @Override
    public ATTR convertToEntityAttribute(DB dbData) {
        if (dbData == null) return null;
        ATTR[] enums = dbType.getEnumConstants();
        for (ATTR e : enums) {
            if (e.getData().equals(dbData)) return e;
        }
        throw new IllegalArgumentException("枚举转化异常" + dbType.getCanonicalName());
    }
}
