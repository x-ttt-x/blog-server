package com.example.backend.service.impl;

import com.example.backend.common.enums.impl.EArticleStatus;
import com.example.backend.common.enums.impl.ECommStatus;
import com.example.backend.common.enums.others.EResCode;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.dao.Article;
import com.example.backend.dao.Tag;
import com.example.backend.pojo.Article.dto.ArticleDto;
import com.example.backend.repository.ArticleContentRep;
import com.example.backend.repository.ArticleRep;
import com.example.backend.repository.CategoryRep;
import com.example.backend.repository.TagRep;
import com.example.backend.service.IArticleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements IArticleService {


    private final ArticleRep articleRep;
    private final CategoryRep categoryRep;
    private final TagRep tagRep;

    private final Specification<Article> spec_active = (root, query, cb) -> cb.equal(root.get("status"), EArticleStatus.DELETED).not();


    /// 增加文章
    @Override
    public void create(ArticleDto articleDto) {
        Article article = new Article();
        CovertToDao(articleDto, article);
        articleRep.save(article);
    }

    @Override
    public List<Article> get() {
        return articleRep.findAll(spec_active);
    }

    @Override
    public Article get(int id) {
        Article article = articleRep.findById(id).orElse(null);
        if (article == null || article.getStatus() == EArticleStatus.DELETED) {
            throw new BusinessException(EResCode.NOT_FOUND);
        } else {
            return article;
        }
    }

    public void update(int id, ArticleDto articleDto) {
        Article article = articleRep.findById(id).orElse(null);
        if (article == null || article.getStatus() == EArticleStatus.DELETED) {
            throw new BusinessException(EResCode.NOT_FOUND);
        } else {
            CovertToDao(articleDto, article);
            articleRep.save(article);
        }
    }

    public void delete(int id) {
        Article article = articleRep.findById(id).orElse(null);
        if (article == null || article.getStatus().equals(EArticleStatus.DELETED)) {
            throw new BusinessException(EResCode.NOT_FOUND);
        }
        article.setStatus(EArticleStatus.DELETED);
        articleRep.save(article);
    }

    public void changeStatus(int id, EArticleStatus status) {
        Article article = articleRep.findById(id).orElse(null);
        if (article == null || article.getStatus().equals(status)) {
            throw new BusinessException(EResCode.NOT_FOUND);
        }
        article.setStatus(status);
        articleRep.save(article);
    }

    public void CovertToDao(ArticleDto articleDto, Article article) {
        /// 设置tags
        articleDto.getTagIdList().forEach(tagId -> {
            tagRep.findById(tagId).ifPresent(tag -> article.getTags().add(tag));
        });
        /// 设置分类
        categoryRep.findById(articleDto.getCategoryId()).ifPresent(article::setCategory);
        articleRep.save(article);
    }
}
