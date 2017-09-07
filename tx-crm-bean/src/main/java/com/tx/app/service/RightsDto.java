package com.tx.app.service;

import java.io.Serializable;

/**
 * 权限Dto，除封装权限信息，还包括该权限所属级别，以及其是否被角色引用标识
 *
 * @author chenqingxia
 */
public class RightsDto implements Serializable {

    private static final long serialVersionUID = 2897644973728740426L;

    // 权限ID
    private Integer id;

    // 父权限ID
    private Integer parentId;

    // 权限名
    private String name;

    // 权限url
    private String url;

    // 权限描述
    private String description;

    // 所属级别
    private Integer level;

    // 权限是否被角色使用
    private String checked = "";


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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RightsDto rightsDto = (RightsDto) o;

        if (checked != null ? !checked.equals(rightsDto.checked) : rightsDto.checked != null) {
            return false;
        }
        if (description != null ? !description.equals(rightsDto.description) : rightsDto.description != null) {
            return false;
        }
        if (id != null ? !id.equals(rightsDto.id) : rightsDto.id != null) {
            return false;
        }
        if (name != null ? !name.equals(rightsDto.name) : rightsDto.name != null) {
            return false;
        }
        if (level != null ? !level.equals(rightsDto.level) : rightsDto.level != null) {
            return false;
        }
        if (parentId != null ? !parentId.equals(rightsDto.parentId) : rightsDto.parentId != null) {
            return false;
        }
        if (url != null ? !url.equals(rightsDto.url) : rightsDto.url != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (checked != null ? checked.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RightsDto{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", parentId=").append(parentId);
        sb.append(", level='").append(level).append('\'');
        sb.append(", checked='").append(checked).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
