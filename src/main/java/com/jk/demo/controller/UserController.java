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
public class UserController {
    private final static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    /**
     * 用户个人订单页面
     * @param userId
     * @param token
     */
    @PostMapping("/user/order")
    public String order(String userId, String token,
                        HttpServletRequest request) {//order list
        ResultBean<List<OrderSTO>> re = new ResultBean<>();
        HttpSession session = request.getSession();
        if (token.equals(session.getAttribute("token").toString())) {//登录用户才可查看订单
            List<OrderSTO> userOrder = userService.getUserOrder(userId);
            re.setData(userOrder);
        } else {
            re.setCode(HttpStatus.BAD_REQUEST);
        }
        return "order_list";
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

    /**
     *   登录， 若成功登录则在session中存入 token 和 userId
     * @param userId  用户id
     * @param password  密码
     */
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
//        if (userService.doRegister(user)) {
        if (user.getEmail().equals("1063@qq.com")) {
            logger.info("注册成功：" + userId + " " + password + " " + email);
            resultBean.setCode(HttpStatus.OK);
            return resultBean;
        } else {
            logger.info("注册失败：" + userId + " " + password + " " + email);
            resultBean.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
            // TODO: 2020/2/19 返回注册失败原因
            return resultBean;
        }
    }
}
