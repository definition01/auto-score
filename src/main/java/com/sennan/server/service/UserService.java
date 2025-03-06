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
     * 创建新用户
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
     * 根据id查找用户
     * @param id
     * @return
     */
    UserVo getById(int id);
}
