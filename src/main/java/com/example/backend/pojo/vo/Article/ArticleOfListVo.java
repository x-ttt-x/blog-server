package com.example.backend.pojo.vo.Article;

import com.example.backend.common.enums.impl.EArticleStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleOfListVo {

    /// id
    private int articleId;
    /// 标题
    private String title;
    /// 总结/摘要
    private String summary;
    /// 备注
    private String desc;
    /// 状态
    private EArticleStatus status;
    /// 创建时间
    private LocalDateTime createdTime;
    /// 更新时间
    private LocalDateTime updatedTime;

}
