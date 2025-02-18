package com.sennan.server.service;

import com.sennan.pojo.dto.UserDto;

public interface UserService {

    /**
     * 注册新用户
     * @param userDto
     */
    void register(UserDto userDto);
}
