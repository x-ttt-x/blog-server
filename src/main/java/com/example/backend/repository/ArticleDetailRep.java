package com.example.backend.repository;

import com.example.backend.dao.ArticleDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDetailRep extends JpaRepository<ArticleDetail, Integer> {
    ArticleDetail getArticleDetailByDetailId(int detailId);
}
