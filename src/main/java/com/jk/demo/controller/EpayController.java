package com.jk.demo.controller;

import com.jk.demo.bean.ResultBean;
import com.jk.demo.dao.Dao_entities.Order;
import com.jk.demo.service.EpayService;
import com.jk.demo.sto.OrderSTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class EpayController {
    @Autowired
    EpayService epayService;
    @PostMapping("/epay/paying")
    public ResultBean paying(String userId, String token,
                             String oid, HttpServletRequest request){
        ResultBean re=new ResultBean<>();
        HttpSession session = request.getSession();
        if(token.equals(session.getAttribute("token"))){
            boolean paying = epayService.paying(oid);
            if(!paying){
                re.setCode(HttpStatus.NOT_FOUND);
            }

        }else{
            re.setCode(HttpStatus.BAD_REQUEST);
        }

        return re;
    }

    @PostMapping("/epay/generateOrder")
    public ResultBean<OrderSTO> generateOrder(String userId, String token,
                                                    String sid, String pid, HttpServletRequest request){
        ResultBean<OrderSTO> re=new ResultBean<>();
        HttpSession session = request.getSession();
        if(token.equals(session.getAttribute("token"))){
            OrderSTO orderSTO = epayService.generateOrder(userId, sid, pid);
            re.setData(orderSTO);
        }else{
            re.setCode(HttpStatus.BAD_REQUEST);
        }

        return re;
    }
}
