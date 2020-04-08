package com.jk.demo;

import com.jk.demo.dao.OrderDao;
import com.jk.demo.service.EpayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    EpayService epayService;
    @Test
    public void contextLoads() {
    }
    @Test
    public void test1(){
        boolean paying = epayService.paying("6a1ae9a4-9c30-4644-a992-8d33d8136c0c");
        System.out.println();
    }

}
