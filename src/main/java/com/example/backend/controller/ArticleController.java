package com.example.backend.controller;

import com.example.backend.common.enums.others.EResCode;
import com.example.backend.common.res.ApiResponse;
import com.example.backend.common.res.ListData;
import com.example.backend.dao.Article;
import com.example.backend.pojo.dto.Article.ArticleDto;
import com.example.backend.pojo.vo.Article.ArticleOfListVo;
import com.example.backend.pojo.vo.Article.ArticleVo;
import com.example.backend.service.impl.ArticleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    public final ArticleServiceImpl articleService;

    @GetMapping("/test")
    public ApiResponse<Article> test() {
        Article article = new Article();
        return ApiResponse.success(article);
    }

    /// 添加文章
    @PostMapping("/add")
    public ApiResponse<Article> add(@RequestBody ArticleDto articleDto) {
        articleService.add(articleDto);
        return ApiResponse.success();
    }

    /// 获取文章列表
    @GetMapping("/getList")
    public ApiResponse<ListData<ArticleOfListVo>> getList(@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "1") int currPage) {
        List<ArticleOfListVo> articlesOfListVo = articleService.getList(pageSize, currPage);
        long total = articleService.getTotal();
        return ApiResponse.success(new ListData<>(articlesOfListVo, total));
    }

    /// 获取文章内容
    @GetMapping("/get")
    public ApiResponse<ArticleVo> get(@RequestParam int id) {
        ArticleVo articleVo = articleService.get(id);
        if (articleVo == null) {
            return ApiResponse.error(EResCode.NOT_FOUND);
        }
        return ApiResponse.success(articleVo);
    }

    /// 更新文章相关内容
    @PostMapping("/update")
    public ApiResponse<Article> update(@Validated @RequestBody ArticleDto articleDto) {
        return ApiResponse.success();
    }

}
