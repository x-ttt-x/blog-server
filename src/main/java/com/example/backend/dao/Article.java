package com.example.backend.dao;

import com.example.backend.common.enums.impl.EArticleStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;


@Table(name = "tb_article")
@Entity
@Data
@DynamicInsert
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private int articleId;

    /// 标题
    @Column(name = "article_title")
    private String title;

    /// 正文
    @Column(name = "article_detail_id")
    private int detailId;

    /// 总结/摘要
    @Column(name = "article_summary")
    private String summary;

    /// 备注
    @Column(name = "article_desc")
    private String desc;

    /// 发布状态,默认为未发布
    @Column(name = "article_status", columnDefinition = "tinyint default 0")
    @Convert(converter = EArticleStatus.Converter.class)
    private EArticleStatus status;

    /// 创建时间
    @Column(name = "article_created_time",nullable = false)
    private LocalDateTime createdTime;

    /// 更新时间
    @Column(name = "article_updated_time",nullable = false)
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


}
