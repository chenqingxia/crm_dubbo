package com.tx.app.action.base;

import com.tx.app.action.BaseController;
import com.tx.app.dao.home.User;
import com.tx.app.form.home.LoginForm;
import com.tx.app.service.UserDto;
import com.tx.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenqingxia
 on 2014/6/19.
 */
@Controller
public class LoginController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value ="/login", method = RequestMethod.GET)
    public String index(HttpServletRequest request,HttpSession session) {
        String no = (String)session.getAttribute("no");
        if(no!=null){
            return "base/module/home";
        }else {
            return "home/login/login-index";
        }
    }

    @RequestMapping(value ="/home/login", method = RequestMethod.POST)
    public String index2(LoginForm form,HttpServletRequest request,HttpSession session,Model model) {
        User user=this.userService.queryByNo(form.getNo());
        System.out.println("no=="+form.getNo());
        if(user!=null) {
            // 查询出用户对应的权限，将其权限URL，以及请求方式放入一个集合中
            List<UserDto> userDtos = this.userService.queryRoleAndRightsByNo(user.getNo());
            List<String> rights = new ArrayList<String>();
            for (UserDto ud : userDtos) {
                rights.add(ud.getUrl());
            }
            // 将一部分信息放到本次回话session中（用户编号，用户ID，用户权限）
            session.setAttribute("no", user.getNo());
            session.setAttribute("name", user.getName());
            session.setAttribute("rights", rights);
        }else{

            model.addAttribute("resultState", FAIL);
            model.addAttribute("errMsg", "用户名或者密码错误！");
            return "home/login/login-index";
        }

        return "base/module/home";
    }
}
