package com.tx.app.service;

import com.tx.app.dao.home.Role;
import com.tx.app.form.home.RoleForm;
import com.tx.app.util.PaginationSupport;
import java.util.List;

public interface RoleService {

    PaginationSupport<RoleDto> search(RoleForm form);

    int remove(List<Integer> ids);

    Role get(Integer id);

    List<Role> getAll();

    Role queryByName(String name);

    int update(RoleForm form);

    int save(RoleForm form);

}
