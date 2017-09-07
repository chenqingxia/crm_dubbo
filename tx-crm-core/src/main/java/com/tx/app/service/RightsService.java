package com.tx.app.service;

import com.tx.app.dao.home.Rights;
import com.tx.app.form.home.RightsForm;
import com.tx.app.util.PaginationSupport;

import java.util.List;

/**
 * 权限管理Service层
 *
 * @author chenqingxia
 */
public interface RightsService {

    PaginationSupport<RightsDto> search(RightsForm form);

    int remove(List<Integer> ids);

    Rights get(Integer id);

    Rights queryByName(String name);

    int update(RightsForm form);

    int save(RightsForm form);

    List<RightsDto> getAllByParentId(int parentId);

    List<RightsDto> getByParentId(int parentId);
}
