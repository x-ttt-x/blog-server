package com.example.backend.service.impl;

import com.example.backend.common.enums.impl.ECommStatus;
import com.example.backend.common.enums.others.EResCode;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.dao.Category;
import com.example.backend.pojo.Category.dto.CategoryDto;
import com.example.backend.pojo.Category.vo.CategoryVo;
import com.example.backend.repository.CategoryRep;
import com.example.backend.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRep categoryRep;

    /// 筛选条件，未删除的分类
    private final Specification<Category> spec_active = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), ECommStatus.ACTIVE);


    /// 创建新分类
    public void create(CategoryDto categoryDto) {
        if (isExist(categoryDto.getName())) {
            /// 名称重复
            throw new BusinessException(EResCode.NAME_REPEAT);
        }
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto, category);
        categoryRep.save(category);
    }

    /// 删除分类
    public void delete(int id) {
        Category category = categoryRep.findById(id).orElse(null);
        if (category == null || category.getStatus() != ECommStatus.ACTIVE) {
            throw new BusinessException(EResCode.NOT_FOUND);
        } else {
            category.setStatus(ECommStatus.DELETED);
            categoryRep.save(category);
        }
    }

    /// 根据id查找分类
    @Override
    public Category get(int id) {
        Category category = categoryRep.findById(id).orElse(null);
        if (category == null || category.getStatus() == ECommStatus.DELETED) {
            throw new BusinessException(EResCode.NOT_FOUND);
        }
        return category;
    }

    /// 获得全部未删除分类
    @Override
    public List<Category> get() {
        return categoryRep.findAll(spec_active);
    }

    /// 更新分类
    @Override
    public void update(CategoryDto categoryDto, int id) {
        if (isExist(categoryDto.getName(), id)) {
            throw new BusinessException(EResCode.NAME_REPEAT);
        }
        Category category = categoryRep.findById(id).orElse(null);
        if (category == null || category.getStatus() == ECommStatus.DELETED) {
            throw new BusinessException(EResCode.NOT_FOUND);
        } else {
            BeanUtils.copyProperties(categoryDto, category);
            categoryRep.save(category);
        }
    }

    /// 获取分类总数
    public int count() {
        return Math.toIntExact(categoryRep.count());
    }

    /// 查询对应的分类名称是否存在
    public Boolean isExist(String name) {
        Specification<Category> spec_same_name = (root, query, cb) -> cb.equal(root.get("name"), name);
        return !categoryRep.findAll(spec_same_name.and(spec_active)).isEmpty();
    }

    public Boolean isExist(String name, int id) {
        Specification<Category> spec_same_name = (root, query, cb) -> cb.equal(root.get("name"), name);
        Specification<Category> spec_different_id = (root, query, cb) -> cb.equal(root.get("id"), id).not();
        return !categoryRep.findAll(spec_same_name.and(spec_different_id)).isEmpty();
    }

    /// 转换为Dto
    public CategoryVo CoverToVo(Category category) {
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return categoryVo;
    }

}
