package com.jk.demo.controller;

import com.jk.demo.bean.ResultBean;
import com.jk.demo.dao.Dao_entities.Book;
import com.jk.demo.entities.Order;
import com.jk.demo.entities.Token;
import com.jk.demo.entities.Type;
import com.jk.demo.entities.User;
import com.jk.demo.service.BookService;
import com.jk.demo.service.UserService;
import com.jk.demo.sto.OrderSTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
@ResponseBody
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @PostMapping("/user/order")
    public ResultBean<List<OrderSTO>> order(String userId, String token,HttpServletRequest request){//order list
        ResultBean<List<OrderSTO>> re = new ResultBean<>();
        HttpSession session = request.getSession();
        if(token.equals(session.getAttribute("token").toString())){//登录用户才可查看订单
            List<OrderSTO> userOrder = userService.getUserOrder(userId);
            re.setData(userOrder);
        }else{
            re.setCode(400);
        }

        return  re;
    }

    @PostMapping("/user/logout")
    public ResultBean logout(String userId, String token,HttpServletRequest request){
        ResultBean re=new ResultBean<>();
        HttpSession session = request.getSession();
        if(token.equals(session.getAttribute("token").toString())){
            session.removeAttribute("token");

        }else{
            re.setCode(500);
        }
        return re;//返回json串
    }

    @PostMapping("/user/login")
    public ResultBean<Token> login(String userId, String password, HttpServletRequest request){
        ResultBean<Token> resultBean=new ResultBean<>();
        //boolean b = userService.doLogin(userId, password);
        HttpSession session = request.getSession();
        if(session.getAttribute("token")!=null){
            Token token = new Token();
            token.setToken(session.getAttribute("token").toString());
            System.out.println("hhhhhhhhhhhhh----------");
            resultBean.setData(token);
        }
        else if(userService.doLogin(userId, password)){
            String uuid = UUID.randomUUID().toString();
            Token token = new Token();
            token.setToken(uuid);
            resultBean.setData(token);
            session.setAttribute("token",uuid);
        }else{
            resultBean.setCode(400);
        }
        return resultBean;

    }

    @PostMapping("/user/register")
    public String register(User user, Model model){
        ResultBean resultBean=new ResultBean();
        if(userService.doRegister(user)){
            model.addAttribute("result",resultBean);
        }else {
            //resultBean.setMsg("注册失败");
            model.addAttribute("result",resultBean);
            return "register";
        }
        return "login";
    }
}
