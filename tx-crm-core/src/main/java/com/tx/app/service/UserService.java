package com.tx.app.service;

import com.tx.app.dao.home.User;

import java.util.List;

public interface UserService {

    int update(User user);

    User queryByNo(String no);

    List<User> getAll();

    int save(User user);

    int remove(Integer id);

    List<UserDto> queryRoleAndRightsByNo(String no);

}
