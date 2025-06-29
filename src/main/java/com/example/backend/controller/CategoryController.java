package com.example.backend.controller;

import com.example.backend.common.exception.BusinessException;
import com.example.backend.common.res.ApiResponse;
import com.example.backend.common.res.ListData;
import com.example.backend.dao.Category;
import com.example.backend.pojo.Category.dto.CategoryDto;
import com.example.backend.pojo.Category.vo.CategoryVo;
import com.example.backend.service.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    /// 添加分类
    @PostMapping
    public ApiResponse<Category> createCategory(@RequestBody CategoryDto categoryDto) {
        try {
            categoryService.create(categoryDto);
            return ApiResponse.success();
        } catch (BusinessException e) {
            return ApiResponse.error(e.getEResCode());
        }
    }


    /// 分页获取分类
    @GetMapping
    public ApiResponse<ListData<CategoryVo>> getAll() {
        try {
            List<Category> categoryList = categoryService.get();
            List<CategoryVo> categoryVoList = new ArrayList<>();
            categoryList.forEach(category -> categoryVoList.add(categoryService.CoverToVo(category)));
            ListData<CategoryVo> res = ListData.<CategoryVo>builder().list(categoryVoList).total(categoryService.count()).build();
            return ApiResponse.success(res);
        } catch (BusinessException e) {
            return ApiResponse.error(e.getEResCode());
        }
    }

    /// 根据id获取分类
    @GetMapping("/{id}")
    public ApiResponse<CategoryVo> get(@PathVariable int id) {
        try {
            Category category = categoryService.get(id);
            return ApiResponse.success(categoryService.CoverToVo(category));
        } catch (BusinessException e) {
            return ApiResponse.error(e.getEResCode());
        }
    }

    /// 更新分类
    @PutMapping("/{id}")
    public ApiResponse<Category> update(@PathVariable int id, @RequestBody CategoryDto categoryDto) {
        try {
            categoryService.update(categoryDto, id);
            return ApiResponse.success();
        } catch (BusinessException e) {
            return ApiResponse.error(e.getEResCode());
        }
    }

    /// 删除分类
    @DeleteMapping("/{id}")
    public ApiResponse<Category> delete(@PathVariable int id) {
        try {
            categoryService.delete(id);
            return ApiResponse.success();
        } catch (BusinessException e) {
            return ApiResponse.error(e.getEResCode());
        }
    }

}
