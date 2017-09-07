package com.tx.app.service.impl;

import com.tx.app.dao.home.UserRole;
import com.tx.app.mapper.home.UserRoleMapper;
import com.tx.app.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> queryByRoleId(int roleId) {
        return this.userRoleMapper.selectByRoleId(roleId);
    }

    @Override
    public int save(UserRole userRole) {
        return this.userRoleMapper.insert(userRole);
    }

    @Override
    public int remove(int userId) {
        return this.userRoleMapper.deleteByUserId(userId);
    }
}
