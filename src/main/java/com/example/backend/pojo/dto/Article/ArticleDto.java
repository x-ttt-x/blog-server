package com.example.backend.pojo.dto.Article;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

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

}
