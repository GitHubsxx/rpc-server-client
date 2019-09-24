package com.sxx.service;

import com.sxx.dto.UserDto;
/**
 * @Author sxx
 * @Description 暴露的接口
 * @Date 9:51 2019/9/24
 **/
public interface UserService {
    UserDto getUser(Long id);
}
