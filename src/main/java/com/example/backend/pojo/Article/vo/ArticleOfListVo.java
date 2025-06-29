package com.example.backend.pojo.Article.vo;

import com.example.backend.common.enums.impl.EArticleStatus;
import com.example.backend.dao.Article;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    /// 文章状态
    private EArticleStatus status;
    /// 分类list
    private CategoryVo category;
    /// 标签list
    private List<TagVo> tagList = new ArrayList<>();
    /// 创建时间
    private LocalDateTime createdTime;
    /// 更新时间
    private LocalDateTime updatedTime;

    @Data
    public static class CategoryVo {
        private int categoryId;
        private String name;
    }

    @Data
    public static class TagVo {
        private int tagId;
        private String name;
        private String color;
    }

    public ArticleOfListVo(Article article) {
        BeanUtils.copyProperties(article, this);
        if (article.getCategory() != null) {
            /// 分类list
            this.category = new CategoryVo();
            BeanUtils.copyProperties(article.getCategory(), category);
        }
        if (article.getTags() != null) {
            article.getTags().forEach(tag -> {
                TagVo tagVo = new TagVo();
                BeanUtils.copyProperties(tag, tagVo);
                this.tagList.add(tagVo);
            });
        }
    }
}
