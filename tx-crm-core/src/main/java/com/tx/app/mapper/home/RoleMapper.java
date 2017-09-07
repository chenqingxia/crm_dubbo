package com.tx.app.mapper.home;

import com.tx.app.dao.home.Role;
import com.tx.app.form.home.RoleForm;
import com.tx.app.service.RoleDto;

import java.util.List;

/**
 * 角色映射
 * User: chenqingxia
 */
public interface RoleMapper {

    List<RoleDto> selectRoles(RoleForm roleForm);

    int countRoles(RoleForm roleForm);

    Role selectById(int id);

    Role selectByName(String name);

    List<Role> selectAll();

    int insert(Role role);

    int update(Role role);

    int deleteByIds(List<Integer> ids);

    int selectRoleId(String name);

}
