package com.example.backend.pojo.vo.Category;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryVo {
    /// id
    private long id;
    /// 分类名称
    private String name;
    /// 分类备注
    private String desc;
    /// 创建时间
    private LocalDateTime createdTime;
    /// 更新时间
    private LocalDateTime updatedTime;
}
