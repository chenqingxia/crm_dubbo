package com.tx.app.mapper.home;

import com.tx.app.dao.home.UserRole;

import java.util.List;

/**
 * 用户角色映射
 * User: chenqingxia
 */
public interface UserRoleMapper {

    int insert(UserRole userRole);

    int deleteByUserId(int userId);

    List<UserRole> selectByRoleId(int roleId);

}
