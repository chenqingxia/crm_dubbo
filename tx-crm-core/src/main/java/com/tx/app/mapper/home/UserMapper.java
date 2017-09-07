package com.tx.app.mapper.home;

import com.tx.app.dao.home.User;
import com.tx.app.service.UserDto;
import java.util.List;

/**
 * 用户映射
 *
 * @author chenqingxia
 */
public interface UserMapper {

    User selectByNo(String no);

    int insert(User user);

    int update(User user);

    List<User> selectAll();

    int delete(int id);

    /**
     * 根据用户no查询出其对应的角色和权限集合
     * @param no
     * @return
     */
    List<UserDto> selectRoleAndRightsByNo(String no);

}
