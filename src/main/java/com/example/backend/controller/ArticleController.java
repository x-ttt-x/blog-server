package com.example.backend.controller;

import com.example.backend.common.enums.impl.EArticleStatus;
import com.example.backend.common.enums.others.EResCode;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.common.res.ApiResponse;
import com.example.backend.dao.Article;
import com.example.backend.pojo.Article.dto.ArticleDto;
import com.example.backend.pojo.Article.vo.ArticleOfListVo;
import com.example.backend.pojo.Article.vo.ArticleVo;
import com.example.backend.service.impl.ArticleServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    public final ArticleServiceImpl articleService;

    @PostMapping
    public ApiResponse<Article> create(@RequestBody ArticleDto articleDto) {
        articleService.create(articleDto);
        return ApiResponse.success();
    }

    @GetMapping("/{id}")
    public ApiResponse<ArticleVo> get(@PathVariable int id) {
        Article article = articleService.get(id);
        return ApiResponse.success(new ArticleVo(article));
    }

    @GetMapping
    public ApiResponse<List<ArticleOfListVo>> getAll() {
        List<Article> articleList = articleService.get();
        return ApiResponse.success(articleList.stream().map(ArticleOfListVo::new).toList());
    }

    @PutMapping("/{id}")
    public ApiResponse<Article> update(@PathVariable int id, @RequestBody ArticleDto articleDto) {
        articleService.update(id, articleDto);
        return ApiResponse.success();
    }


    @DeleteMapping("/{id}")
    public ApiResponse<Article> delete(@PathVariable int id) {
        articleService.delete(id);
        return ApiResponse.success();
    }

    @PatchMapping("/{id}")
    public ApiResponse<Article> patch(@PathVariable int id, @RequestParam String status) {
        if (!EArticleStatus.isEnumNameExist(status) || status.equals("DELETED")) {
            return ApiResponse.error(EResCode.INVALID_PARAMETER);
        }
        articleService.changeStatus(id, EArticleStatus.valueOf(status));
        return ApiResponse.success();
    }


    @ExceptionHandler(BusinessException.class)
    public ApiResponse<Article> handleBusinessException(BusinessException e) {
        return ApiResponse.error(e.getEResCode());
    }

}
