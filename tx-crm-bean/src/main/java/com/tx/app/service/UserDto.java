package com.tx.app.service;

import java.io.Serializable;

/**
 * 用户Dto
 *
 * @author chenqingxia
 */
public class UserDto implements Serializable {

    private static final long serialVersionUID = 2392810700493480710L;
    // 用户ID
    private Integer userId;
    // 用户编号
    private String userNo;
    // 用户名
    private String userName;
    // 角色ID
    private Integer roleId;
    // 权限ID
    private Integer rightsId;
    // 权限URL
    private String url;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserDto userDto = (UserDto) o;

        if (rightsId != null ? !rightsId.equals(userDto.rightsId) : userDto.rightsId != null) {
            return false;
        }
        if (roleId != null ? !roleId.equals(userDto.roleId) : userDto.roleId != null) {
            return false;
        }
        if (userId != null ? !userId.equals(userDto.userId) : userDto.userId != null) {
            return false;
        }
        if (userName != null ? !userName.equals(userDto.userName) : userDto.userName != null) {
            return false;
        }
        if (userNo != null ? !userNo.equals(userDto.userNo) : userDto.userNo != null) {
            return false;
        }
        if (url != null ? !url.equals(userDto.url) : userDto.url != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userNo != null ? userNo.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        result = 31 * result + (rightsId != null ? rightsId.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDto{");
        sb.append("userId=").append(userId);
        sb.append(", userNo='").append(userNo).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", roleId=").append(roleId);
        sb.append(", rightsId=").append(rightsId);
        sb.append(", url=").append(url);
        sb.append('}');
        return sb.toString();
    }
}
