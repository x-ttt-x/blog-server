package com.example.backend.service;


import com.example.backend.pojo.dto.Article.ArticleDto;
import com.example.backend.pojo.dto.Article.ArticleUpdateDto;
import com.example.backend.pojo.vo.Article.ArticleOfListVo;
import com.example.backend.pojo.vo.Article.ArticleVo;

import java.util.List;

public interface IArticleService {

    void add(ArticleDto articleDto);

    /// 删除也算在更新里面
    void update(ArticleUpdateDto articleUpdateDto);

    ArticleVo get(int id);

    List<ArticleOfListVo> getList(int pageSize, int currPage);

    long getTotal();


}
