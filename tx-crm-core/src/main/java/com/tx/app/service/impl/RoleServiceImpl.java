package com.tx.app.service.impl;

import com.tx.app.dao.home.Role;
import com.tx.app.mapper.home.RoleMapper;
import com.tx.app.service.RoleDto;
import com.tx.app.service.RoleService;
import com.tx.app.form.home.RoleForm;
import com.tx.app.util.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PaginationSupport<RoleDto> search(RoleForm fm) {
        List<RoleDto> list = this.roleMapper.selectRoles(fm);
        int totalCount = this.roleMapper.countRoles(fm);
        return new PaginationSupport(list, totalCount, fm.getiDisplayLength(), fm.getiDisplayStart());
    }

    @Override
    public int remove(List<Integer> ids) {
        return this.roleMapper.deleteByIds(ids);
    }

    @Override
    public Role get(Integer id) {
        return this.roleMapper.selectById(id);
    }

    @Override
    public List<Role> getAll() {
        return this.roleMapper.selectAll();
    }

    @Override
    public Role queryByName(String name) {
        return this.roleMapper.selectByName(name);
    }

    @Override
    public int update(RoleForm form) {
        Role role = this.roleMapper.selectById(form.getId());
        role.setName(form.getName());
        role.setDescription(form.getDescription());
        return this.roleMapper.update(role);
    }

    @Override
    public int save(RoleForm form) {
        Role role = new Role();
        role.setName(form.getName());
        role.setDescription(form.getDescription());
        this.roleMapper.insert(role);
        Role role2=this.roleMapper.selectByName(form.getName());
        return role2.getId();
    }
}
