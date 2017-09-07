package com.tx.app.service;

import com.tx.app.dao.home.RoleRights;

import java.util.List;

/**
 * 角色_权限管理Service层
 *
 * @author chenqingxia
 */
public interface RoleRightsService {

    List<RoleRights> queryByRightsId(int rightsId);

    List<RoleRights> queryByRoleId(int roleId);

    int save(RoleRights roleRights);

    int remove(List<Integer> roleIds);
}
