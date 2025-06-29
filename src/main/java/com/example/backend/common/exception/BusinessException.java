package com.example.backend.common.exception;

import com.example.backend.common.enums.others.EResCode;
import lombok.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
public class BusinessException extends RuntimeException {
    /// 错误码
    private final int code;
    /// 错误消息
    private final String message;
    /// 错误ECode
    private final EResCode eResCode;
    /// 错误详情
    private final Map<Integer, String> details = new HashMap<>();
    /// 时间戳
    private final long timestamp = System.currentTimeMillis();

    /// 单一错误
    public BusinessException(EResCode ECode) {
        this.code = ECode.getCode();
        this.message = ECode.getMsg();
        this.eResCode = ECode;
        details.put(ECode.getCode(), ECode.getMsg());
    }
}
