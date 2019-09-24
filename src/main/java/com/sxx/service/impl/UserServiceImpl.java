package com.sxx.service.impl;

import com.sxx.dto.UserDto;
import com.sxx.service.UserService;

public class UserServiceImpl implements UserService {
    public UserDto getUser(Long id) {
        UserDto dto = new UserDto();
        dto.setUserName("张三");
        dto.setAge(18);
        dto.setSex("男");
        return dto;
    }
}
