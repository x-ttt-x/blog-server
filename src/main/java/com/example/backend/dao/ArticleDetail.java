package com.example.backend.dao;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "tb_article_detail")
@Entity
@Data
public class ArticleDetail {

    @Id
    @Column(name = "article_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int detailId;


    @Column(name = "article_detail_content", columnDefinition = "TEXT")
    private String detailContent;
}
