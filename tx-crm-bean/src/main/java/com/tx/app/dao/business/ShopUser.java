package com.tx.app.dao.business;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: chenqingxia
 * Date: 2014/6/25
 * Time: 9:32
 */
public class ShopUser implements Serializable {

    private static final long serialVersionUID = 5902350249728027223L;
    //登陆名
    private String Login_Name;
    //商户名
    private String Shop_Name;
    //商户地址
    private String Shop_Address;
    //是否删除
    private String Is_Del;
    //创建时间
    private String Created_Time;
    //修改时间
    private String Updated_Time;

    public String getLogin_Name() {
        return Login_Name;
    }

    public void setLogin_Name(String login_Name) {
        Login_Name = login_Name;
    }

    public String getShop_Name() {
        return Shop_Name;
    }

    public void setShop_Name(String shop_Name) {
        Shop_Name = shop_Name;
    }

    public String getShop_Address() {
        return Shop_Address;
    }

    public void setShop_Address(String shop_Address) {
        Shop_Address = shop_Address;
    }

    public String getIs_Del() {
        return Is_Del;
    }

    public void setIs_Del(String is_Del) {
        Is_Del = is_Del;
    }

    public String getCreated_Time() {
        return Created_Time;
    }

    public void setCreated_Time(String created_Time) {
        Created_Time = created_Time;
    }

    public String getUpdated_Time() {
        return Updated_Time;
    }

    public void setUpdated_Time(String updated_Time) {
        Updated_Time = updated_Time;
    }
}
