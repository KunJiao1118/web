package com.jk.demo.controller;

import com.jk.demo.service.EpayService;
import com.jk.demo.service.UserService;
import com.jk.demo.sto.OrderSTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    UserService userService;
    @Autowired
    EpayService epayService;

    /**
     * 加入购物车
     * @param sid  商店id
     * @param pid  商品id
     */
    @GetMapping("/addToShopCast/{sid}/{pid}")
    public String addToShopCast(@PathVariable String sid, @PathVariable String pid, Model model, HttpSession session) {
        String userId = session.getAttribute("userId").toString();
        epayService.generateOrder(userId, sid, pid);
        return "redirect:/book/detail/" + pid;//todo 为什么跳转到图书条目页面?
    }

    /**
     * 个人订单页面
     *
     * @return
     */
    @GetMapping("/allDetails")
    public String allDetails() {
        return "redirect:/getAllOrders/1";
    }

    @GetMapping("/payedDetails")
    public String payedDetails() {
        return "redirect:/getPayedOrders";
    }

    @GetMapping("/unpayedDetails")
    public String unpayedDetails() {
        return "redirect:/getUnayedOrders";
    }

    @GetMapping("/getAllOrders/{pageId}")
    public String getAllOrders(Model model, @PathVariable int pageId, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            model.addAttribute("errorMsg", "用户未登录");
            return "error";
        }
        String userId = session.getAttribute("userId").toString();
        List<OrderSTO> orders = userService.getUserOrder(userId);
        List<OrderSTO> userOrders = userService.getUserOrder(userId, String.valueOf((pageId - 1) * 2), "2");
        model.addAttribute("bookList", userOrders);
        model.addAttribute("currentPage", pageId);
        model.addAttribute("totalRecordCnt", orders.size());//总共的结果数
        int size = orders.size() % 2 == 0 ? orders.size() / 2 : (orders.size() / 2 + 1);
        model.addAttribute("pageCnt", size);//总共的页数
        return "all_details";
    }

    @GetMapping("/getPayedOrders")
    public String getPayedAllOrders(Model model, HttpSession session) {
        String userId = session.getAttribute("userId").toString();
        List<OrderSTO> orders = userService.getUserOrderByState(userId, "已支付");
        model.addAttribute("bookList", orders);
        return "payed_details";
    }

    @GetMapping("/getUnayedOrders")
    public String getUnpayedAllOrders(Model model, HttpSession session) {
        String userId = session.getAttribute("userId").toString();
        List<OrderSTO> orders = userService.getUserOrderByState(userId, "未支付");
        model.addAttribute("bookList", orders);
        return "unpayed_details";
    }

    @GetMapping("/deleteToShopCast/{oid}")
    public String deleteToShopCast(@PathVariable String oid, Model model) {
        boolean b = epayService.delOrder(oid);
        return "redirect:/getAllOrders/1";
    }
}
