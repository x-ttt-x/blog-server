package com.example.backend.dao;

import com.example.backend.common.enums.impl.ECommStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@DynamicInsert
@Table(name = "tb_tag")
public class Tag {

    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tagId;

    @Column(name = "tag_name")
    private String name;

    @Column(name = "tag_color")
    private String color;

    @Column(name = "tag_desc")
    private String desc;

    @Column(name = "tag_status", columnDefinition = "tinyint default 0")
    private ECommStatus status;

    @ManyToMany(mappedBy = "tags")
    private Set<Article> articles = new HashSet<>();

    @Column(name = "tag_created_time")
    private LocalDateTime createdTime;

    @Column(name = "tag_updated_time")
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
