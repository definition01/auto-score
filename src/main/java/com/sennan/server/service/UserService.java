package com.sennan.server.service;

import com.sennan.common.enumeration.OperationType;
import com.sennan.pojo.dto.UserDto;
import com.sennan.pojo.entity.User;
import com.sennan.pojo.vo.UserVo;
import com.sennan.server.annotation.AutoFill;

public interface UserService {

    /**
     * 注册新用户
     * @param userDto
     */
    void register(UserDto userDto);

    /**
     * 登录
     * @param userDto
     */
    User login(UserDto userDto);


    /**
     * 修改用户信息
     * @param userDto
     */
    @AutoFill(OperationType.UPDATE)
    void update(UserDto userDto);

    /**
     * 根据名字查找用户
     * @return
     */
    UserVo getByUserName();
}
