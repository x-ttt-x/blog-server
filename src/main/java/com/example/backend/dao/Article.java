package com.example.backend.dao;

import com.example.backend.common.enums.impl.EArticleStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.util.*;


@Table(name = "tb_article")
@Entity
@Data
/// 只插入非空数据
@DynamicInsert
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private int articleId;

    /// 标题
    @Column(name = "article_title")
    private String title;

    /// 正文
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false, mappedBy = "article")
    private ArticleContent content;

    /// 总结/摘要
    @Column(name = "article_summary")
    private String summary;

    /// 备注
    @Column(name = "article_desc")
    private String desc;

    /// 分类
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("articles")
    private Category category;

    /// 标签list
    @ManyToMany
    @JoinTable(name = "tb_article_tag_link", joinColumns = @JoinColumn(name = "article_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    /// 发布状态,默认为未发布
    @Column(name = "article_status", columnDefinition = "tinyint default 0")
    @Convert(converter = EArticleStatus.Converter.class)
    private EArticleStatus status;

    /// 创建时间
    @Column(name = "article_created_time")
    private LocalDateTime createdTime;

    /// 更新时间
    @Column(name = "article_updated_time")
    private LocalDateTime updatedTime;

    @PrePersist
    protected void onCreate() {
        //自动生成创建时间和更新时间
        this.createdTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        //自动生成更新时间
        this.updatedTime = LocalDateTime.now();
    }


    /// 设置content
    public void setContent(String content) {
        if (this.content == null) {
            this.content = new ArticleContent();
            this.content.setArticle(this); // 必须设置反向关联
        }
        this.content.setContent(content);
    }

    /// 获取content
    public String getContent() {
        return this.content.getContent();
    }

}
