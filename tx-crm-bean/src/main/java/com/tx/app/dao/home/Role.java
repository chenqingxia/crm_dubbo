package com.tx.app.dao.home;

import java.io.Serializable;

/**
 * 角色实体类
 * User: chenqingxia
 */
public class Role implements Serializable {

    private static final long serialVersionUID = 5953294099897268741L;

    // id,唯一标识
    private Integer id;

    // 角色名
    private String name;

    // 角色描述
    private String description;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Role role = (Role) o;

        if (description != null ? !description.equals(role.description) : role.description != null) {
            return false;
        }
        if (id != null ? !id.equals(role.id) : role.id != null) {
            return false;
        }
        if (name != null ? !name.equals(role.name) : role.name != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
