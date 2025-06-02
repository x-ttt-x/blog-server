package com.example.backend.service.impl;

import com.example.backend.dao.Category;
import com.example.backend.pojo.dto.CategoryDto;
import com.example.backend.pojo.vo.Category.CategoryVo;
import com.example.backend.repository.CategoryRep;
import com.example.backend.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRep categoryRep;

    public void add(CategoryDto categoryDto) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto, category);
        categoryRep.save(category);
    }

    @Override
    public List<CategoryVo> getList(int pageSize, int currPage) {
        PageRequest pageRequest = PageRequest.of(currPage - 1, pageSize);
        List<Category> categoryList = categoryRep.findAll(pageRequest).getContent();
        return categoryList.stream().map(category -> {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(category, categoryVo);
            return categoryVo;
        }).collect(Collectors.toList());
    }

    @Override
    public long getTotal() {
        return categoryRep.count();
    }


}
