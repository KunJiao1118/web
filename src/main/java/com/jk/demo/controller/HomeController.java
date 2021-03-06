package com.jk.demo.controller;

import com.jk.demo.bean.QualityEnum;
import com.jk.demo.bean.ResultBean;
import com.jk.demo.bean.TypeStr;
import com.jk.demo.dao.Dao_entities.Book;
import com.jk.demo.dao.Dao_entities.ShopBook;
import com.jk.demo.service.BookService;
import com.jk.demo.sto.BookSTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final static Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    BookService bookService;

    /**
     * 首页全站搜索。根据content搜索全站书籍名称中包含该字段的书。
     * 对搜索内容进行分页，每页50条数据， 根据前端pageId确定返回哪一页。
     *
     * @param content 搜索内容
     * @param pageId  搜索结果后端进行分页，根据pageId返回 第pageId页 ; 每页最多50个结果
     * @param type    搜索种类；可能值参见{@link TypeStr}
     * @param quality 品相  （可为空） 参见{@link QualityEnum}
     * @param model   前端使用的数据包
     */
    @GetMapping("/home/search")
    public String search(String content, Integer pageId, String type, String quality,
                         Model model) {
        logger.info("[content:" + content + ",pageId:" + pageId + ",type:" + type + ",quality:" + quality + "]");
        if (pageId == null) pageId = 1; //若没有传，默认为1
        if (type == null) type = TypeStr.ITEM;//若没有传，默认为"商品"种类；

        ///////////////////////////////////////  TODO 完成全站搜索逻辑
        List<ShopBook> books = bookService.fuzzySearch(content, type, pageId, quality);

        ///////////////////////////////////////
        //根据model数据生成网页
        model.addAttribute("bookList", books);//搜索结果列表
        logger.info(books);
        model.addAttribute("content", content);
        model.addAttribute("currentPage", pageId);
        model.addAttribute("type", type);
        model.addAttribute("totalRecordCnt", books.size());//总共的结果数
        model.addAttribute("pageCnt", books.size() / 50 + 1);//总共的页数，每页50个数据
        return "search";
    }

    /**
     * 首页页面
     * 好书推荐，暂时随便推荐12本书，暂时未确定好书标准
     */
    @GetMapping("")
    public String mainPage(Model model) {
        List<Book> bookByRecommend = bookService.getBookByRecommend();
        logger.info(bookByRecommend.toString());
        model.addAttribute("lists", bookByRecommend);
        return "first_page";
    }

}
