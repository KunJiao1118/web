package com.jk.demo.controller;

import com.jk.demo.bean.ResultBean;
import com.jk.demo.dao.Dao_entities.Order;
import com.jk.demo.service.EpayService;
import com.jk.demo.sto.OrderSTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class EpayController {
    @Autowired
    EpayService epayService;
    @PostMapping("/epay/generateOrder")
    public ResultBean<OrderSTO> generateOrder(String userId, String token,
                                                    String sid, String pid, HttpServletRequest request){
        ResultBean<OrderSTO> re=new ResultBean<>();
        HttpSession session = request.getSession();
        if(token.equals(session.getAttribute("token"))){
            OrderSTO orderSTO = epayService.generateOrder(userId, sid, pid);
            re.setData(orderSTO);
        }else{
            re.setCode(400);
        }

        return re;
    }
}
