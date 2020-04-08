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
        String userId = session.getAttribute("userId").toString();
        List<OrderSTO> orders = userService.getUserOrder(userId);
        List<OrderSTO> userOrders = userService.getUserOrder(userId, String.valueOf((pageId - 1) * 2), "2");
        model.addAttribute("bookList",userOrders);
        model.addAttribute("currentPage", pageId);
        model.addAttribute("totalRecordCnt", orders.size());//总共的结果数
        int size = orders.size()%2==0?orders.size()/2:(orders.size()/2+1);
        model.addAttribute("pageCnt", size);//总共的页数
        return "all_details";
    }

    @GetMapping("/getPayedOrders")
    public String getPayedAllOrders(Model model, HttpSession session) {
        String userId = session.getAttribute("userId").toString();
        List<OrderSTO> orders = userService.getUserOrderByState(userId,"已支付");
        model.addAttribute("bookList",orders);
        return "payed_details";
    }

    @GetMapping("/getUnayedOrders")
    public String getUnpayedAllOrders(Model model, HttpSession session) {
        String userId = session.getAttribute("userId").toString();
        List<OrderSTO> orders = userService.getUserOrderByState(userId,"未支付");
        model.addAttribute("bookList",orders);
        return "unpayed_details";
    }

    @GetMapping("/deleteToShopCast/{oid}")
    public String deleteToShopCast(@PathVariable String oid, Model model) {
        boolean b = epayService.delOrder(oid);
        return "redirect:/getAllOrders/1";
    }
  /*  @GetMapping("/addToShopCast/{pid}")
    public String addToShopCast(@PathVariable String pid,Model model,HttpSession session) {
        String userId = session.getAttribute("userId").toString();
        epayService.generateOrder(userId,"1",pid);
        return "redirect:/bookIndex/"+pid;
    }*/
 /*   @GetMapping("/moreRecommendBooks/{pageId}")
    public String moreRecommendBooks(Model model, @PathVariable int pageId) {
        List<Book> allBooks = bookService.getAllBookByRecommend();
        List<Book> books = bookService.getBookByRecommend(String.valueOf((pageId-1)*20),"20");
        model.addAttribute("bookList",books);
        model.addAttribute("currentPage", pageId);
        model.addAttribute("totalRecordCnt", allBooks.size());//总共的结果数
        model.addAttribute("pageCnt", allBooks.size()/20+1);//总共的页数
        return "more_recommendbooks";
    }*/
}
