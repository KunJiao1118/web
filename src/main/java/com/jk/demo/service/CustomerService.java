package com.jk.demo.service;

import com.jk.demo.entities.Customer;
import com.jk.demo.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class CustomerService implements CustomerMapper {
    @Autowired
    CustomerMapper customerMapper;
    public Collection<Customer> getAll(){
        return customerMapper.getAll();
    }
    public Customer findById(Integer id){
        return customerMapper.findById(id);
    }
    public void update(Customer customer){
        customerMapper.update(customer);
    }
    public void save(Customer customer){
        customerMapper.save(customer);
    }
}
