package com.example.backend.dao;

import com.example.backend.common.enums.impl.EUserStatus;
import com.example.backend.common.enums.impl.EUserType;
import jakarta.persistence.*;

@Table(name="tb_user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer userId;


    /// 账号
    @Column(name="user_name")
    private String userName;


    /// 账号昵称
    @Column(name="nick_name")
    private String nickName;


    @Column(name="password")
    private String password;


    @Column(name="user_type", nullable = false,columnDefinition = "tinyint default 0")
    @Convert(converter = EUserType.Converter.class)
    private EUserType userType;

    @Column(name="user_status",nullable = false,columnDefinition = "tinyint default 0")
    @Convert(converter = EUserStatus.Converter.class)
    private EUserStatus userStatus;
}
