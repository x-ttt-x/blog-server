package com.example.backend.common.enums.others;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EResCode {
    /// 成功
    SUCCESS(200, "访问成功"),

    /// 参数错误
    INVALID_PARAMETER(400, "请求参数错误"),

    /// 名称重复
    NAME_REPEAT(400, "名称重复"),


    /// 没找到资源
    NOT_FOUND(404, "资源不存在");


    private final int code;
    private final String msg;
}
