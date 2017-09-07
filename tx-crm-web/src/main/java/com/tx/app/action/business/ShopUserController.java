package com.tx.app.action.business;

import com.tx.app.action.BaseController;
import com.tx.app.annotation.Security;
import com.tx.app.dao.business.ShopUser;
import com.tx.app.form.business.ShopUserForm;
import com.tx.app.service.ShopUserService;
import com.tx.app.util.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: chenqingxia
 * Date: 2014/6/25
 * Time: 14:22
 */
@Controller
@RequestMapping("/business")
public class ShopUserController extends BaseController{
    @Autowired
    private ShopUserService shopUserService;

    //@Security(url = "/business/shopuser")
    @RequestMapping(value = "/shopuser", method = RequestMethod.GET)
    public String index(){
      return "/business/shopuser/shopuser_index";
    }

    //@Security(url = "/business/shopuser")
    @RequestMapping(value = "/shopuser/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(ShopUserForm form, HttpServletRequest request) {
        form.setSortInfo(request);
        PaginationSupport<ShopUser> ps = this.shopUserService.search(form);
        return dataTableJson(ps.getTotalCount(), ps.getItems());
    }
}
