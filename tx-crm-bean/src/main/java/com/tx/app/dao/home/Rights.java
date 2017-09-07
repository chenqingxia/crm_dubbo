package com.tx.app.dao.home;

import java.io.Serializable;

/**
 * 权限
 * User: chenqingxia
 */
public class Rights implements Serializable {

    private static final long serialVersionUID = 5902350249728027222L;

    // 唯一标识
    private Integer id;

    // 父级id
    private Integer parentId;

    // 权限名
    private String name;

    // 权限对应的URL
    private String url;

    // 权限描述
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

        Rights rights = (Rights) o;

        if (description != null ? !description.equals(rights.description) : rights.description != null) {
            return false;
        }
        if (id != null ? !id.equals(rights.id) : rights.id != null) {
            return false;
        }
        if (name != null ? !name.equals(rights.name) : rights.name != null) {
            return false;
        }
        if (parentId != null ? !parentId.equals(rights.parentId) : rights.parentId != null) {
            return false;
        }
        if (url != null ? !url.equals(rights.url) : rights.url != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rights{");
        sb.append("id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
