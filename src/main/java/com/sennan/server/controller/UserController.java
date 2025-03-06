package com.sennan.server.controller;


import com.sennan.common.constant.JwtClaimsConstant;
import com.sennan.common.properties.JwtProperties;
import com.sennan.common.result.ApiResponse;
import com.sennan.common.util.JwtUtil;
import com.sennan.pojo.dto.UserDto;
import com.sennan.pojo.entity.User;
import com.sennan.pojo.vo.UserVo;
import com.sennan.server.inceptor.JwtInterceptor;
import com.sennan.server.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    JwtProperties jwtProperties;



    @ApiOperation("注册新用户")
    @PostMapping("/register")
    public ApiResponse register(@RequestBody UserDto userDto){
        userService.register(userDto);
        return ApiResponse.success("新建成功");
    }

    

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ApiResponse login(@RequestBody UserDto userDto)
    {
        User user = userService.login(userDto);
        HashMap<String,Object> claims = new HashMap();
        claims.put(JwtClaimsConstant.USERNAME,userDto.getUsername());
        String token = JwtUtil.createJWT(jwtProperties.getSecretKey(), jwtProperties.getTtl(), claims);

        UserVo userVo = new UserVo();
        userVo.setUserName(user.getUsername());
        userVo.setPassword(user.getPassword());
        userVo.setToken(token);

        return ApiResponse.success(userVo);
    }


    @ApiOperation("修改信息")
    @PutMapping("/insert")
    public ApiResponse update(@RequestBody UserDto userDto)
    {
        userService.update(userDto);
        return ApiResponse.success("修改成功");
    }

    @ApiOperation("根据id查找用户")
    @PutMapping("/{id}")
    public ApiResponse getById(@PathVariable int id)
    {
        UserVo userVo = userService.getById(id);
        return ApiResponse.success(userVo);
    }

}
