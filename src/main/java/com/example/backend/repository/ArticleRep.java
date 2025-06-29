package com.example.backend.repository;

import com.example.backend.dao.Article;
import com.example.backend.dao.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleRep extends JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article> {
}
