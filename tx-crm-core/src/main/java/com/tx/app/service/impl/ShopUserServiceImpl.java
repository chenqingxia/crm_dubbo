package com.tx.app.service.impl;

import com.tx.app.dao.business.ShopUser;
import com.tx.app.form.business.ShopUserForm;
import com.tx.app.mapper.business.ShopUserMapper;
import com.tx.app.mapper.home.RoleMapper;
import com.tx.app.service.ShopUserService;
import com.tx.app.util.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenqingxia
 * Date: 2014/6/25
 * Time: 10:42
 */
@Service
public class ShopUserServiceImpl implements ShopUserService {

    @Autowired
    private ShopUserMapper shopUserMapper;

    @Override
    public PaginationSupport<ShopUser> search(ShopUserForm form) {
        List<ShopUser> list=this.shopUserMapper.selectShopUser(form);
        int totalCount=this.shopUserMapper.countShopUser(form);
        return new PaginationSupport<ShopUser>(list,totalCount,form.getiDisplayLength(),form.getiDisplayStart());
    }
}
