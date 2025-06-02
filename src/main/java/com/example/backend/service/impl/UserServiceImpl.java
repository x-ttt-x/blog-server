package com.example.backend.service.impl;

import com.example.backend.dao.User;
import com.example.backend.pojo.dto.UserDto;
import com.example.backend.repository.UserRep;
import com.example.backend.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRep userRep;

    @Override
    public void add(UserDto user) {
        User userEntity = new User();
        BeanUtils.copyProperties(user, userEntity);
        userRep.save(userEntity);
    }
}
