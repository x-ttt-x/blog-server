package com.example.backend.dao;

import com.example.backend.common.enums.impl.ECommStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table(name = "tb_category")
@Data
@Entity
@DynamicInsert
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(name = "category_name")
    private String name;

    @Column(name = "category_desc")
    private String desc;

    @Column(name = "category_status", columnDefinition = "tinyint default 0")
    private ECommStatus status;

    @Column(name = "category_created_time")
    private LocalDateTime createdTime;

    @Column(name = "category_updated_time")
    private LocalDateTime updatedTime;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("category")
    private Set<Article> article = new HashSet<>();


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
