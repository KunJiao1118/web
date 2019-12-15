package com.jk.demo.mapper;

import com.jk.demo.entities.User;
import org.apache.ibatis.annotations.*;

import java.util.Collection;

@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") String id);

    @Select("select * from user")
    Collection<User> getAll();
    @Insert("insert into user(id,username,name,gender,department,role,contact,email) " +
            "values(#{user.id},#{user.username},#{user.name},#{user.gender},#{user.department},#{user.role},#{user.contact},#{user.email})")
    void saveUser(@Param("user") User user);
    @Update("update user set username = #{user.id},name = #{user.name},gender = #{user.gender},department = #{user.department},role = #{user.role},contact = #{user.contact},email = #{user.email} where id = #{user.id}")
    void updateUser(@Param("user") User user);
    @Delete("delete from user where id = #{id}")
    void delete(@Param("id") String id);
}
