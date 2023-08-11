package com.zgd.menhu.demo.dao;

import com.zgd.menhu.demo.model.UserModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select( "select * from user limit 1")
    UserModel getUser(@Param("name") String name);

    @Select( "select * from user ")
    List<UserModel> getUserList(@Param("name") String name);

    @Insert( "insert  into user (name, password) values (#{name}, #{password})"
    )
    int registerUser(@Param("name") String name, @Param("password") String password);

    @Insert( "insert  into user (name, phone, password) values (#{name}, #{phone}, 0)"
    )
    int addUser(@Param("name") String name, @Param("phone") String phone);
}
