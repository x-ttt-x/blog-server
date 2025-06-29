package com.example.backend.pojo.Tag.Vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TagVo {

    private int tagId;
    private String name;
    private String desc;
    private String color;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
