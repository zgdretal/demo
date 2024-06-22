package com.zgd.menhu.demo.dao;

import com.zgd.menhu.demo.model.UserModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select( "select * from user limit 1")
    UserModel getUser(@Param("name") String name);

    @Select( "select * from user where id=#{id}")
    UserModel getUserById(@Param("id") Integer id);

    @Select( "select * from user ")
    List<UserModel> getUserList(@Param("name") String name);

    @Insert( "insert  into user (name, password) values (#{name}, #{password})"
    )
    int registerUser(@Param("name") String name, @Param("password") String password);

    @Insert( "insert  into user (name, phone, group_id, password) values (#{name}, #{phone}, #{groupId},0)"
    )
    int addUser(@Param("name") String name, @Param("phone") String phone, @Param("groupId") Integer groupId);

    @Delete("delete from user where id =#{id}")
    int deleteUserById(@Param("id") String id);

    @Select( "select * from user where name like CONCAT('%', #{search}, '%') ")
    List<UserModel> search(@Param("search") String search);
}
