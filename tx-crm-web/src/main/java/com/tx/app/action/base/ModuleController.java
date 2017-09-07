package com.tx.app.action.base;

import com.tx.app.action.BaseController;
import com.tx.app.annotation.Security;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 后台管理入口
 *
 * @author Jamyma
 */
@Controller
@RequestMapping("/module")
public class ModuleController extends BaseController {

    /**
     * 个人中心
     * base/module/home
     * @return
     */
    @Security(url = "/module/home")
    @RequestMapping(value ="/home", method = RequestMethod.GET)
    public String index() {
        return "base/module/home";
    }

    /**
     * 商务
     * /module/business
     * @return
     */
    @Security(url = "/module/business")
    @RequestMapping(value = "/business", method = RequestMethod.GET)
    public String business() {
        return "base/module/business";
    }

    /**
     * 设备
     * @return
     */
    //@Security(url = "/module/equipment")
    @RequestMapping(value = "/equipment", method = RequestMethod.GET)
    public String equipment() {
        return "base/module/equipment";
    }
}
