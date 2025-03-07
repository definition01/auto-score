package com.sennan.server.service.impl;


import com.sennan.common.constant.MessageConstant;
import com.sennan.common.context.BaseContext;
import com.sennan.common.exception.LoginFailedException;
import com.sennan.common.exception.RegisterFailedException;
import com.sennan.pojo.dto.UserDto;
import com.sennan.pojo.entity.User;
import com.sennan.pojo.vo.UserVo;
import com.sennan.server.mapper.UserMapper;
import com.sennan.server.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserMapper userMapper;
    /**
     * 注册新用户
     * @param userDto
     */
    public void register(UserDto userDto) {
        User user = userMapper.findByName(userDto.getUsername());
        String password = userDto.getPassword();
        if(user==null) {
            User user1 = new User();
            //对密码进行加密
            password = DigestUtils.md5DigestAsHex(password.getBytes());

            user1.setCreateUser(userDto.getUsername());
            user1.setUpdateUser(userDto.getUsername());
            user1.setCreateTime(LocalDateTime.now());
            user1.setUpdateTime(LocalDateTime.now());
            BeanUtils.copyProperties(userDto, user1);
            user1.setPassword(password);
            userMapper.add(user1);
        }else{
            throw new RegisterFailedException(MessageConstant.ALREADY_EXISTS);
        }
    }

    /**
     * 用户登录
     * @param userDto
     */
    public User login(UserDto userDto) {

        User user = userMapper.findByName(userDto.getUsername());
        String password = userDto.getPassword();
        password=DigestUtils.md5DigestAsHex(password.getBytes());
        if(user == null)
        {
            throw new LoginFailedException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        if(!password.equals(user.getPassword()))
        {
            throw new LoginFailedException(MessageConstant.PASSWORD_ERROR);
        }else {

            return user;
        }

    }

    /**
     * 修改用户信息
     * @param userDto
     */
    public void update(UserDto userDto) {
        String password = userDto.getPassword();
        password=DigestUtils.md5DigestAsHex(password.getBytes());
        userDto.setPassword(password);
        User user = new User();
        BeanUtils.copyProperties(user,userDto);
        user.setId(userMapper.getByUserName(BaseContext.getCurrentName()).getId());
        userMapper.update(user);
    }

    /**
     * 根据名字查找用户信息
     * @return
     */
    public UserVo getByUserName() {
        User user = userMapper.getByUserName(BaseContext.getCurrentName());
        UserVo userVo = new UserVo();
        userVo.setUserName(user.getUsername());
        userVo.setPassword("********");
        userVo.setId(user.getId());
        return userVo;
    }


}
