package com.example.backend.pojo.dto.Article;


import lombok.Data;

@Data
public class ArticleUpdateDto {

    /// id
    private int articleId;
    /// 标题
    private String title;
    /// 正文
    private String content;
    /// 总结/摘要
    private String summary;
    /// 备注
    private String desc;
    /// 状态

}
