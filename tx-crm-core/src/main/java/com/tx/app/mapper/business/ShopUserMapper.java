package com.tx.app.mapper.business;

import com.tx.app.dao.business.ShopUser;
import com.tx.app.form.business.ShopUserForm;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenqingxia
 * Date: 2014/6/25
 * Time: 9:48
 */
public interface ShopUserMapper {

    List<ShopUser> selectShopUser(ShopUserForm form);

    int countShopUser(ShopUserForm form);
}
