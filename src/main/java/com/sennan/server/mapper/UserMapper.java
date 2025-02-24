package com.sennan.server.mapper;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sennan.common.enumeration.OperationType;
import com.sennan.pojo.dto.UserDto;
import com.sennan.pojo.entity.User;
import com.sennan.server.annotation.AutoFill;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Value;

@Mapper
public interface UserMapper {

    @Select("select * from users where username = #{username}")
    User findByName(String name);


    @Insert("insert into users(username,password,create_user,update_user,create_time,update_time)"+
    "values"
    +"(#{username},#{password},#{createUser},#{updateUser},#{createTime},#{updateTime})")
    void update(User user);
}
