package com.tx.app.service;


import com.tx.app.dao.business.ShopUser;
import com.tx.app.form.business.ShopUserForm;
import com.tx.app.util.PaginationSupport;

/**
 * Created with IntelliJ IDEA.
 * User: chenqingxia
 * Date: 2014/6/25
 * Time: 9:42
 */
public interface ShopUserService {
    PaginationSupport<ShopUser> search(ShopUserForm form);
}
