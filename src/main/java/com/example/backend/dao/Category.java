package com.example.backend.dao;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Table(name = "tb_category")
@Data
@Entity
@DynamicInsert
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_desc")
    private String categoryDesc;

    @Column(name = "category_created_time")
    private LocalDateTime createdTime;

    @Column(name = "category_updated_time")
    private LocalDateTime updatedTime;


    @PrePersist
    protected void onCreate() {
        //自动生成创建时间和更新时间
        this.createdTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        //自动生成更新时间
        this.updatedTime = LocalDateTime.now();
    }


}
