package com.example.backend.repository;

import com.example.backend.dao.Category;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface CategoryRep extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {
    void getCategoryByName(String name);

    Integer countCategoryByName(String name);

    Category getCategoryByCategoryId(int categoryId);

}
