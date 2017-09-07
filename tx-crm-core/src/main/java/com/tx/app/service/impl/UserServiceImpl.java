package com.tx.app.service.impl;

import com.tx.app.dao.home.User;
import com.tx.app.mapper.home.UserMapper;
import com.tx.app.service.UserDto;
import com.tx.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int update(User user) {
        return this.userMapper.update(user);
    }

    @Override
    public User queryByNo(String no) {
        return this.userMapper.selectByNo(no);
    }

    @Override
    public List<User> getAll() {
        return this.userMapper.selectAll();
    }

    @Override
    public int save(User user) {
        return this.userMapper.insert(user);
    }

    @Override
    public int remove(Integer id) {
        return this.userMapper.delete(id);
    }

    @Override
    public List<UserDto> queryRoleAndRightsByNo(String no) {
        return this.userMapper.selectRoleAndRightsByNo(no);
    }
}
