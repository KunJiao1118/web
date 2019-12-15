package com.jk.demo.mapper;

import com.jk.demo.entities.Me;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MeMapper {
    @Insert("insert into me(username,head,password,number,email) values(#{me.username},#{me.head},#{me.password},#{me.number},#{me.email})")
    void save(@Param("me") Me me);
    @Select("select * from me where username = #{username}")
    Me getByName(@Param("username") String username);
    @Update("update me set head = #{me.head},password = #{me.password},number = #{me.number},email = #{me.email} where username = #{me.username}")
    void update(@Param("me") Me me);
}
