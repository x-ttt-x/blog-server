package com.example.backend.controller;

import com.example.backend.pojo.dto.UserDto;
import com.example.backend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController  //接口方法返回对象 转换成json文本
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping("/add")
    public String addUser(@RequestBody UserDto user) {
        userServiceImpl.add(user);
        return "success";
    }

    @GetMapping("/get")
    public String getUser() {
        return "success";
    }
}
