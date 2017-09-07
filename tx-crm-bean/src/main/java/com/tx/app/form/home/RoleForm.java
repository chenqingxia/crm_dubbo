package com.tx.app.form.home;

import com.tx.app.form.BaseTableForm;
import java.util.List;

/**
 * 角色表单
 * User: chenqingxia
 */
public class RoleForm extends BaseTableForm {

    // id,唯一标识
    private Integer id;

    // 角色名
    private String name;

    // 角色描述
    private String description;

    // 角色对应的权限
    private List<Integer> rights;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getRights() {
        return rights;
    }

    public void setRights(List<Integer> rights) {
        this.rights = rights;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RoleForm{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", rights='").append(rights).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
