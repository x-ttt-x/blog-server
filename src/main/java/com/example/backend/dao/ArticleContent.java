package com.example.backend.dao;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;

import java.util.Objects;

@Table(name = "tb_article_content_link")
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ArticleContent {

    @Id
    @Column(name = "article_id")
    private int articleId;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "article_id")
    private Article article;

}
