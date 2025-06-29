package com.example.backend.pojo.Article.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ArticleDto {

    /// 标题
    private String title;
    /// 正文
    private String content;
    /// 总结/摘要
    private String summary;
    /// 备注
    private String desc;
    /// 分类Id
    private int categoryId;
    /// 标签idList
    private List<Integer> tagIdList;
}
