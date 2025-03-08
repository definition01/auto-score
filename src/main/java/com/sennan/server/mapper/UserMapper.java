package com.sennan.server.mapper;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sennan.common.enumeration.OperationType;
import com.sennan.pojo.dto.UserDto;
import com.sennan.pojo.entity.User;
import com.sennan.pojo.vo.UserVo;
import com.sennan.server.annotation.AutoFill;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;

@Mapper
public interface UserMapper {

    /**
     * 根据用户名来查询用户
     * @param name
     * @return
     */
    @Select("select * from users where username = #{username}")
    User findByName(String name);


    /**
     * 新增员工信息
     * @param user
     */
    @Insert("insert into users(username,password,create_user,update_user,create_time,update_time)"+
    "values"
    +"(#{username},#{password},#{createUser},#{updateUser},#{createTime},#{updateTime})")
    void add(User user);


    /**
     * 修改员工信息
     * @param user
     *
     */
    @AutoFill(value = OperationType.INSERT)
    void update(User user);


    /**
     * 根据username询用户
     * @param username
     */
    @Select("select * from users where username = #{username}")
    User getByUserName(String username);
}
