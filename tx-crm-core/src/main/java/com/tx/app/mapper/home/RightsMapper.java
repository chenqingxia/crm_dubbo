package com.tx.app.mapper.home;

import com.tx.app.dao.home.Rights;
import com.tx.app.service.RightsDto;
import com.tx.app.form.home.RightsForm;

import java.util.List;

/**
 * 权限映射
 * User: chenqingxia
 */
public interface RightsMapper {

    List<RightsDto> selectRights(RightsForm rightsForm);

    int countRights(RightsForm rightsForm);

    Rights selectById(int id);

    Rights selectByName(String name);

    int insert(Rights rights);

    int update(Rights rights);

    int deleteByIds(List<Integer> ids);

    /**
     * 按照父级权限查询出所有其所有子类权限，按照父子级排序号
     */
    List<RightsDto> selectAllByParentId(int parentId);

    List<RightsDto> selectByParentId(int parentId);
}
