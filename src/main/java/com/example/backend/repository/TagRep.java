package com.example.backend.repository;

import com.example.backend.dao.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TagRep extends JpaRepository<Tag, Integer>, JpaSpecificationExecutor<Tag> {
    Tag findByName(String name);
}
