package com.example.backend.controller;

import com.example.backend.common.res.ApiResponse;
import com.example.backend.common.res.ListData;
import com.example.backend.dao.Category;
import com.example.backend.pojo.dto.CategoryDto;
import com.example.backend.pojo.vo.Category.CategoryVo;
import com.example.backend.service.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @PostMapping("/add")
    public ApiResponse<Category> addCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.add(categoryDto);
        return ApiResponse.success();
    }

    @GetMapping("/getList")
    public ApiResponse<ListData<CategoryVo>> getList(@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "1") int currPage) {
        List<CategoryVo> categoryVoList = categoryService.getList(pageSize, currPage);
        long total = categoryService.getTotal();
        return ApiResponse.success(new ListData<>(categoryVoList, total));
    }
}
