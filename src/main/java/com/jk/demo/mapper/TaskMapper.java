package com.jk.demo.mapper;

import com.jk.demo.entities.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;

@Mapper
public interface TaskMapper {
    @Select("select * from task where name = #{name}")
    Task findByName(@Param("name") String name);

    @Select("select * from task")
    Collection<Task> getAll();
    @Select("select * from task where isdone = '是'")
    Collection<Task> getDone();
    @Select("select * from task where isdone = '否'")
    Collection<Task> getNotDone();
    /*
    @Insert("insert into user(id,username,name,gender,department,role,contact,email) " +
            "values(#{user.id},#{user.username},#{user.name},#{user.gender},#{user.department},#{user.role},#{user.contact},#{user.email})")
    void saveUser(@Param("user") User user);
    */
}
