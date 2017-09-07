package com.tx.app.dao.home;

import java.io.Serializable;

/**
 * 用户角色关系
 * User: chenqingxia
 */
public class UserRole implements Serializable {

    private static final long serialVersionUID = 3390027834439621968L;

    // 唯一标识
    private Integer id;

    // 用户ID
    private Integer userId;

    // 角色ID
    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserRole userRole = (UserRole) o;

        if (id != null ? !id.equals(userRole.id) : userRole.id != null) {
            return false;
        }
        if (roleId != null ? !roleId.equals(userRole.roleId) : userRole.roleId != null) {
            return false;
        }
        if (userId != null ? !userId.equals(userRole.userId) : userRole.userId != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserRole{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", roleId=").append(roleId);
        sb.append('}');
        return sb.toString();
    }
}
