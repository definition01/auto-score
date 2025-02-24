package com.sennan.server.service;

import com.sennan.pojo.dto.UserDto;
import com.sennan.pojo.entity.User;

public interface UserService {

    /**
     * 注册新用户
     * @param userDto
     */
    void register(UserDto userDto);

    /**
     * 创建新用户
     * @param userDto
     */
    User login(UserDto userDto);
}
