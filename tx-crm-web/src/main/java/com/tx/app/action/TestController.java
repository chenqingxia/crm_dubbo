package com.tx.app.action;

import com.tx.api.IProcessData;
import com.tx.app.service.ShopUserService;
import com.tx.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/api")
public class TestController extends BaseController {
//
    @Autowired
    private IProcessData iProcessData;

    @Autowired
    private ShopUserService shopUserService;

    @RequestMapping(value = "/testconsumer", method = RequestMethod.GET)
    @ResponseBody
    public String test(HttpServletRequest request) {
        int aaa = 1;
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"appContext-consumer.xml"});
//        context.start();
//        IProcessData demoService = (IProcessData)context.getBean("demoService");
        String hello = iProcessData.deal("nihao");
        System.out.println(hello);
        return hello;

    }

    @RequestMapping(value = "/testfun1", method = RequestMethod.GET)
    @ResponseBody
    public String TestFun1(Model model){
        //String url = this.userService.service1(123);
        return null;//url;
    }

    @RequestMapping(value = "/testfun2", method = RequestMethod.GET)
    @ResponseBody
    public String TestFun2(Model model){
        //String url = this.userService.service2(456);
        return null;//url;
    }
}