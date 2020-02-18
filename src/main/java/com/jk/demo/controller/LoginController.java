package com.jk.demo.controller;

import com.jk.demo.bean.ResultBean;
import com.jk.demo.entities.Token;
import com.jk.demo.entities.User;
import com.jk.demo.service.BookService;
import com.jk.demo.service.UserService;
import com.jk.demo.sto.OrderSTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
@ResponseBody
public class LoginController {
    private final static Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @PostMapping("/user/order")
    public ResultBean<List<OrderSTO>> order(String userId, String token, HttpServletRequest request) {//order list
        ResultBean<List<OrderSTO>> re = new ResultBean<>();
        HttpSession session = request.getSession();
        if (token.equals(session.getAttribute("token").toString())) {//登录用户才可查看订单
            List<OrderSTO> userOrder = userService.getUserOrder(userId);
            re.setData(userOrder);
        } else {
            re.setCode(HttpStatus.BAD_REQUEST);
        }
        return re;
    }

    @PostMapping("/user/logout")
    public ResultBean logout(String userId, String token, HttpServletRequest request,
                             HttpServletResponse response) {
        ResultBean<String> re = new ResultBean<>();
        HttpSession session = request.getSession();
        if(session==null || session.getAttribute("token")==null){
            re.setCode(HttpStatus.BAD_REQUEST);
            re.setData("用户未登录");
            logger.info("注销返回:" + re);
            return re;
        }
        logger.info("sessionId:" + session.getId() + " " + userId + " " +  token);
        if (token.equals(session.getAttribute("token").toString())) {
            session.removeAttribute("token");//判断用户是否登录的标志，用于网页模板生成

            session.invalidate();
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    cookie.setMaxAge(0);//设置生命为0 ，删除cookie
                    response.addCookie(cookie);
                    break;
                }
            }
        } else {
            re.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("注销返回:" + re);
        return re;//返回json串
    }

    @PostMapping("/user/login")
    public ResultBean<Token> login(String userId, String password, HttpServletRequest request,
                                   HttpServletResponse response) {
        ResultBean<Token> resultBean = new ResultBean<>();
        if (userId.isEmpty() || password.isEmpty()) {
            resultBean.setCode(HttpStatus.BAD_REQUEST);
            return resultBean;
        }
        HttpSession session = request.getSession();
        logger.info("[" + userId + " " + password + "]");
        if (session.getAttribute("token") != null) {
            Token token = new Token();
            token.setToken(session.getAttribute("token").toString());
            resultBean.setData(token);
            return resultBean;
        } else if (userService.doLogin(userId, password)) {
            String uuid = UUID.randomUUID().toString();
            Token token = new Token();
            token.setToken(uuid);
            resultBean.setData(token);
            session.setAttribute("token", uuid);
            session.setAttribute("userId", userId);

            Cookie cookie = new Cookie("token", uuid);
            cookie.setPath("/");
            response.addCookie(cookie);
            cookie = new Cookie("userId", userId);
            cookie.setPath("/");
            response.addCookie(cookie);

            return resultBean;
        } else {
            resultBean.setCode(HttpStatus.UNAUTHORIZED);
            return resultBean;
        }
    }

    @PostMapping("/user/register")
    public ResultBean register(String userId, String password,String email) {
        ResultBean resultBean = new ResultBean();
        User user = new User();
        user.setUsername(userId);
        user.setPassword(password);
        user.setEmail(email);
        if (userService.doRegister(user)) {
            resultBean.setCode(HttpStatus.OK);
            return resultBean;
        } else {
            resultBean.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return resultBean;
        }
    }
}
