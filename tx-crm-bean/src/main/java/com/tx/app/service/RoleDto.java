package com.tx.app.service;

import java.io.Serializable;

/**
 * 角色Dto，封装角色信息，及其所对应的权限名称
 *
 * @author chenqingxia
 */
public class RoleDto implements Serializable {

    private static final long serialVersionUID = 8357665076690217619L;

    // 角色ID
    private Integer id;

    // 角色名
    private String name;

    // 角色描述
    private String description;

    // 角色对应的权限（权限名按，分隔拼接）
    private String rights;

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

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RoleDto roleDto = (RoleDto) o;

        if (description != null ? !description.equals(roleDto.description) : roleDto.description != null) {
            return false;
        }
        if (id != null ? !id.equals(roleDto.id) : roleDto.id != null) {
            return false;
        }
        if (name != null ? !name.equals(roleDto.name) : roleDto.name != null) {
            return false;
        }
        if (rights != null ? !rights.equals(roleDto.rights) : roleDto.rights != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (rights != null ? rights.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RoleDto{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", rights='").append(rights).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
