package com.tx.app.dao.home;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息管理
 *
 * @author chenqingxia
 */
public class User implements Serializable {

    private static final long serialVersionUID = -981427642238139462L;

    private Integer id;
    // 工号
    private String no;
    // 中文姓名
    private String name;
    // 邮箱
    private String email;
    // 手机
    private String mobile;
    // 描述
    private String description;
    // 最后登录ip
    private String lastIp;
    // 最后登录时间
    private Date lastTime;
    // 是否锁定,0:锁定,1:未锁定
    private Integer locked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user= (User) o;

        if (description != null ? !description.equals(user.description) : user.description != null) {
            return false;
        }
        if (email != null ? !email.equals(user.email) : user.email != null) {
            return false;
        }
        if (id != null ? !id.equals(user.id) : user.id != null) {
            return false;
        }
        if (lastIp != null ? !lastIp.equals(user.lastIp) : user.lastIp != null) {
            return false;
        }
        if (lastTime != null ? !lastTime.equals(user.lastTime) : user.lastTime != null) {
            return false;
        }
        if (locked != null ? !locked.equals(user.locked) : user.locked != null) {
            return false;
        }
        if (mobile != null ? !mobile.equals(user.mobile) : user.mobile != null) {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        if (no != null ? !no.equals(user.no) : user.no != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (no != null ? no.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (lastIp != null ? lastIp.hashCode() : 0);
        result = 31 * result + (lastTime != null ? lastTime.hashCode() : 0);
        result = 31 * result + (locked != null ? locked.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", no='").append(no).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", lastIp='").append(lastIp).append('\'');
        sb.append(", lastTime=").append(lastTime);
        sb.append(", locked=").append(locked);
        sb.append('}');
        return sb.toString();
    }
}
