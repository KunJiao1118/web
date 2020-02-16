package com.jk.demo.service.impl;

import com.jk.demo.dao.OrderDao;
import com.jk.demo.dao.UserDao;
import com.jk.demo.entities.User;
import com.jk.demo.service.UserService;
import com.jk.demo.sto.OrderSTO;
import com.jk.demo.utils.CodeUtil;
import com.jk.demo.utils.MailUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    UserDao userDao=UserDao.getInstance();
    OrderDao orderDao=OrderDao.getInstance();
    public User getByName(String username) {
        //userMapper.getByName(username);
        User user=new User();
        user.setUsername("123");
        user.setPassword("123");
        return user;
    }

    @Override
    public List<OrderSTO> getUserOrder(String userId) {
        ArrayList<OrderSTO> allsUserorders = orderDao.findAllsUserorders(userId);

        return allsUserorders;
    }

    @Override
    public boolean doLogin(String userId, String password) {
        boolean login = userDao.login(userId, password);
        return login;
    }

    public boolean doRegister(User user) {
        //利用正则表达式（可改进）验证邮箱是否符合邮箱的格式
        if(!user.getEmail().matches("^\\w+@(\\w+\\.)+\\w+$")){
            return false;
        }
        //生成激活码
        String code= CodeUtil.generateUniqueCode();
        //User user=new User(userName,email,password,0,code);
        //将用户保存到数据库
        //UserDao userDao=new UserDaoImpl();
        //保存成功则通过线程的方式给用户发送一封邮件
        if(true){//userDao.save(user)>0
            new Thread(new MailUtil(user.getEmail(), code)).start();;
            return true;
        }
        return false;
        //return true;
    }
}
