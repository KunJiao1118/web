package com.jk.demo.controller;

import com.jk.demo.bean.ResultBean;
import com.jk.demo.dao.Dao_entities.Order;
import com.jk.demo.service.EpayService;
import com.jk.demo.sto.OrderSTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class EpayController {
    @Autowired
    EpayService epayService;


    /**
     * 收银台页面
     * 生成订单后，点击确认支付按钮跳转到结算结果页面
     *
     * @param userId
     * @param token   用户token
     * @param sid     店铺id
     * @param pid     商品id
     * @param request
     * @return
     */
    @PostMapping("/epay/generateOrder")
    public String generateOrder(Model model,
                                String userId, String token, String sid, String pid,
                                HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (token.equals(session.getAttribute("token"))) {
            OrderSTO orderSTO = epayService.generateOrder(userId, sid, pid);
        }

        ///////////////////////////
        model.addAttribute("bookList", null);//订单书籍列表
        model.addAttribute("oid",null);//订单id
        return "generate_order";
    }

    /**
     * 结算结果页面
     * @param userId
     * @param token
     * @param oid
     * @param request
     * @return
     */
    @PostMapping("/epay/paying")
    public String paying(Model model, String userId, String token,
                         String oid, HttpServletRequest request) {
        ResultBean re = new ResultBean<>();
        HttpSession session = request.getSession();
        if (token.equals(session.getAttribute("token"))) {
            boolean paying = epayService.paying(oid);
            if (!paying) {
                re.setCode(HttpStatus.NOT_FOUND);
            }

        } else {
            re.setCode(HttpStatus.BAD_REQUEST);
        }

        ////////////////////
        model.addAttribute("status",null);//支付结果，可以返回一个字符串
        return "paid";
    }
}
