package com.example.backend.service;

import com.example.backend.dao.Category;
import com.example.backend.pojo.Category.dto.CategoryDto;

import java.util.List;

public interface ICategoryService {

    /// 增
    void create(CategoryDto categoryDto);

    /// 删
    void delete(int id);

    /// 查
    Category get(int id);

    /// 查全量
    List<Category> get();

    /// 查
    void update(CategoryDto categoryDto, int id);


}
