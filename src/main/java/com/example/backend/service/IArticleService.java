package com.example.backend.service;


import com.example.backend.dao.Article;
import com.example.backend.pojo.Article.dto.ArticleDto;

import java.util.List;

public interface IArticleService {

    void create(ArticleDto articleDto);

    List<Article> get();

    Article get(int id);

}
