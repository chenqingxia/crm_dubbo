package com.tx.app.service;

import com.tx.app.dao.home.UserRole;

import java.util.List;

public interface UserRoleService {

    List<UserRole> queryByRoleId(int roleId);

    int save(UserRole userRole);

    int remove(int userId);

}
