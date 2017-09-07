package com.tx.app.dao.home;

import java.io.Serializable;

/**
 * 角色权限关系
 * User: chenqingxia
 */
public class RoleRights implements Serializable {

    private static final long serialVersionUID = 4473783464681112692L;

    // 唯一标识
    private Integer id;

    // 角色ID
    private Integer roleId;

    //权限ID
    private Integer rightsId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRightsId() {
        return rightsId;
    }

    public void setRightsId(Integer rightsId) {
        this.rightsId = rightsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RoleRights that = (RoleRights) o;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (rightsId != null ? !rightsId.equals(that.rightsId) : that.rightsId != null) {
            return false;
        }
        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        result = 31 * result + (rightsId != null ? rightsId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RoleRights{");
        sb.append("id=").append(id);
        sb.append(", roleId=").append(roleId);
        sb.append(", rightsId=").append(rightsId);
        sb.append('}');
        return sb.toString();
    }
}
