package com.example.backend.repository;

import com.example.backend.dao.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRep extends JpaRepository<Article, Integer> {
    Article getArticleByArticleId(int articleId);
}
