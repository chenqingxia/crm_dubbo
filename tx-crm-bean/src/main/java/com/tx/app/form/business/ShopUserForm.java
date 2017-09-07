package com.tx.app.form.business;

import com.tx.app.form.BaseTableForm;

/**
 * Created with IntelliJ IDEA.
 * User: chenqingxia
 * Date: 2014/6/25
 * Time: 9:30
 */
public class ShopUserForm extends BaseTableForm {
    //登陆名
    private String Login_Name;
    //商户名
    private String Shop_Name;
    //是否删除
    private String Is_Del;

    public String getShop_Name() {
        return Shop_Name;
    }

    public void setShop_Name(String shop_Name) {
        Shop_Name = shop_Name;
    }

    public String getLogin_Name() {
        return Login_Name;
    }

    public void setLogin_Name(String login_Name) {
        Login_Name = login_Name;
    }

    public String getIs_Del() {
        return Is_Del;
    }

    public void setIs_Del(String is_Del) {
        Is_Del = is_Del;
    }
}
