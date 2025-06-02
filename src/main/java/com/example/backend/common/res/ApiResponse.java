package com.example.backend.common.res;

import com.example.backend.common.enums.others.EResCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@JsonPropertyOrder({"code", "msg", "data", "timestamp"})
public class ApiResponse<T> {

    private final int code;
    private final String msg;

    /// 去除空data中的null数据
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    /// 自动生成响应时间
    @Builder.Default
    private final long timestamp = System.currentTimeMillis();

    /// 成功响应，无返回
    public static <T> ApiResponse<T> success() {
        return ApiResponse.<T>builder()
                .code(EResCode.SUCCESS.getCode())
                .msg(EResCode.SUCCESS.getMsg())
                .build();
    }

    /// 成功响应，有返回
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .code(EResCode.SUCCESS.getCode())
                .msg(EResCode.SUCCESS.getMsg())
                .data(data)
                .build();
    }

    /// 成功相应，自定义返回msg
    public static <T> ApiResponse<T> success(T data, String msg) {
        return ApiResponse.<T>builder()
                .code(EResCode.SUCCESS.getCode())
                .msg(msg)
                .data(data)
                .build();
    }

    /// 成功相应，自定义返回code和msg,在枚举EResCode中定义并使用
    public static <T> ApiResponse<T> success(T data, EResCode ECode) {
        return ApiResponse.<T>builder()
                .code(ECode.getCode())
                .msg(ECode.getMsg())
                .data(data)
                .build();
    }

    /// 错误
    public static <T> ApiResponse<T> error(EResCode ECode) {
        return ApiResponse.<T>builder()
                .code(ECode.getCode())
                .msg(ECode.getMsg())
                .build();
    }


}
