package com.sennan.server.controller;


import com.sennan.common.result.Result;
import com.sennan.pojo.dto.UserDto;
import com.sennan.server.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @ApiOperation("注册新用户")
    @PutMapping("/register")
    public Result register(@RequestBody UserDto userDto){

        userService.register(userDto);
        return Result.success();
    }
}
