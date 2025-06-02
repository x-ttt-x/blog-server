package com.example.backend.common.enums.others;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EResCode {
    /// 成功
    SUCCESS(200, "访问成功"),

    /// 没找到资源
    NOT_FOUND(404, "资源不存在");

    private final int code;
    private final String msg;
}
