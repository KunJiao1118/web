package com.jk.demo.mapper;

import com.jk.demo.entities.Customer;
import org.apache.ibatis.annotations.*;

import java.util.Collection;

@Mapper
public interface CustomerMapper {
    @Select("select * from cus where id = #{id}")
    Customer findById(@Param("id") Integer id);
    @Select("select * from cus")
    Collection<Customer> getAll();
    @Update("update cus set sname = #{cus.sname},name = #{cus.name} where id = #{cus.id}")
    void update(@Param("cus") Customer cus);
    @Insert("insert into cus(id,sname,name) values(#{cus.id},#{cus.sname},#{cus.name})")
    void save(@Param("cus") Customer cus);
}
