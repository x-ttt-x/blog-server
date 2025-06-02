package com.example.backend.service;

import com.example.backend.pojo.dto.CategoryDto;
import com.example.backend.pojo.vo.Category.CategoryVo;

import java.util.List;

public interface ICategoryService {

    void add(CategoryDto articleDto);

    /// 删除也算在更新里面
//    void update(CategoryUpdateDto articleUpdateDto);

//    CategoryVo get(int id);

    List<CategoryVo> getList(int pageSize, int currPage);

    long getTotal();

}
