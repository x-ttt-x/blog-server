package com.example.backend.service.impl;

import com.example.backend.dao.Article;
import com.example.backend.dao.ArticleDetail;
import com.example.backend.pojo.dto.Article.ArticleDto;
import com.example.backend.pojo.dto.Article.ArticleUpdateDto;
import com.example.backend.pojo.vo.Article.ArticleOfListVo;
import com.example.backend.pojo.vo.Article.ArticleVo;
import com.example.backend.repository.ArticleDetailRep;
import com.example.backend.repository.ArticleRep;
import com.example.backend.service.IArticleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements IArticleService {


    private final ArticleRep articleRep;
    private final ArticleDetailRep articleDetailRep;

    @Transactional
    @Override
    public void add(ArticleDto articleDto) {
        ArticleDetail articleDetailEntity = new ArticleDetail();
        /// 设置正文
        articleDetailEntity.setDetailContent(articleDto.getContent());
        ArticleDetail detailRes = articleDetailRep.save(articleDetailEntity);
        /// 设置文章相关信息
        Article articleEntity = new Article();
        articleEntity.setDetailId(detailRes.getDetailId());
        BeanUtils.copyProperties(articleDto, articleEntity);
        articleRep.save(articleEntity);
    }

    @Transactional
    /// 更新文章
    @Override
    public void update(ArticleUpdateDto articleDto) {
        ArticleDetail articleDetailEntity = new ArticleDetail();
        BeanUtils.copyProperties(articleDto, articleDetailEntity);
    }

    @Override
    public List<ArticleOfListVo> getList(int pageSize, int currPage) {
        PageRequest pageRequest = PageRequest.of(currPage - 1, pageSize);
        List<Article> articleList = articleRep.findAll(pageRequest).getContent();
        return articleList.stream().map(article -> {
            ArticleOfListVo articleOfListVo = new ArticleOfListVo();
            BeanUtils.copyProperties(article, articleOfListVo);
            return articleOfListVo;
        }).collect(Collectors.toList());
    }


    @Override
    public ArticleVo get(int id) {
        Article article = articleRep.getArticleByArticleId(id);
        if (article == null) return null;
        ArticleDetail articleDetail = articleDetailRep.getArticleDetailByDetailId(article.getDetailId());
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
        articleVo.setContent(articleDetail.getDetailContent());
        return articleVo;
    }


    @Override
    public long getTotal() {
        return articleRep.count();
    }


}
