package com.example.backend.repository;

import com.example.backend.dao.ArticleContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleContentRep extends JpaRepository<ArticleContent, Integer> {

}
