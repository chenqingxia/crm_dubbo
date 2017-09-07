package com.tx.app.mapper.home;

import com.tx.app.dao.home.RoleRights;

import java.util.List;

/**
 * 角色权限映射
 * User: chenqingxia
 */
public interface RoleRightsMapper {

    int insert(RoleRights roleRights);

    int deleteByRoleIds(List<Integer> roleIds);

    List<RoleRights> selectByRoleId(int roleId);

    List<RoleRights> selectByRightsId(int rightsId);
}
