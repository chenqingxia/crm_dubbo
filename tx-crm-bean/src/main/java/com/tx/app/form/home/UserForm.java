package com.tx.app.form.home;

import com.tx.app.form.BaseTableForm;
import java.util.List;

/**
 * 用户表单
 *
 * @author chenqingxia
 */
public class UserForm extends BaseTableForm {

    private List<Integer> roles;

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserForm{");
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
