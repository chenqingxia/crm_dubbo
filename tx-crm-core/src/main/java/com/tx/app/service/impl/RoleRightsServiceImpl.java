package com.tx.app.service.impl;

import com.tx.app.dao.home.RoleRights;
import com.tx.app.mapper.home.RoleRightsMapper;
import com.tx.app.service.RoleRightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色_权限管理Service层实现类
 *
 * @author of753
 */
@Service
public class RoleRightsServiceImpl implements RoleRightsService {

    @Autowired
    private RoleRightsMapper roleRightsMapper;

    @Override
    public List<RoleRights> queryByRightsId(int rightsId) {
        return this.roleRightsMapper.selectByRightsId(rightsId);
    }

    @Override
    public List<RoleRights> queryByRoleId(int roleId) {
        return this.roleRightsMapper.selectByRoleId(roleId);
    }

    @Override
    public int save(RoleRights roleRights) {
        return this.roleRightsMapper.insert(roleRights);
    }

    @Override
    public int remove(List<Integer> roleIds) {
        return this.roleRightsMapper.deleteByRoleIds(roleIds);
    }
}
