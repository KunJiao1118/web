package com.jk.demo.controller;

import com.jk.demo.entities.Customer;
import com.jk.demo.entities.User;
import com.jk.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/cuss")
    public String list(Model model){
        Collection<Customer> cuss = customerService.getAll();
        model.addAttribute("cuss",cuss);
        return "cus";
    }

    @GetMapping("/emp_cus")
    public String toAddPage(){
        return "add_cus";
    }

    @PostMapping("/emp_cus")
    public String addCus(Customer customer){

        System.out.println("保存的用户信息"+customer.toString());
        if(customerService.findById(customer.getId()) == null)
            customerService.save(customer);
        else customerService.update(customer);
        //来到员工列表页面
        return "redirect:/cuss";
    }
    @GetMapping("/emp_cus/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Customer byId = customerService.findById(id);
        model.addAttribute("cus",byId);
        return "add_cus";

    }

}
