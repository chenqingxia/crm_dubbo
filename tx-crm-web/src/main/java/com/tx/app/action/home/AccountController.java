package com.tx.app.action.home;

import com.tx.app.action.BaseController;
import com.tx.app.annotation.Security;
import com.tx.app.dao.home.User;
import com.tx.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * 帐号信息
 *
 * @author chenqingxia
 */
@Controller
@RequestMapping("/home")
public class AccountController extends BaseController {

    @Autowired
    private UserService useService;

    @Security(url = "/home/account")
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String index(HttpSession session, Model model) {;
        String no = (String)session.getAttribute("no");
        User user = this.useService.queryByNo(no);
        model.addAttribute("user", user);
        return "/home/account/account-index";
    }

}
