package com.tx.app.form.home;

import com.tx.app.form.BaseTableForm;

/**
 * Created by chenqingxia
 * on 2014/6/21.
 */
public class LoginForm extends BaseTableForm {
    private String no;
    private String name;
    private String password;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
