package com.example.backend.repository;

import com.example.backend.dao.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRep extends JpaRepository<Category, Integer> {
}
