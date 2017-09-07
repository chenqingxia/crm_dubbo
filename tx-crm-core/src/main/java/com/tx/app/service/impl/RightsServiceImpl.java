package com.tx.app.service.impl;

import com.tx.app.dao.home.Rights;
import com.tx.app.mapper.home.RightsMapper;
import com.tx.app.service.RightsDto;
import com.tx.app.service.RightsService;
import com.tx.app.form.home.RightsForm;
import com.tx.app.util.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限管理Service层实现类
 *
 * @author of753
 */
@Service
public class RightsServiceImpl implements RightsService {

    @Autowired
    private RightsMapper rightsMapper;

    @Override
    public PaginationSupport<RightsDto> search(RightsForm form) {
        List<RightsDto> list = this.rightsMapper.selectRights(form);
        int totalCount = this.rightsMapper.countRights(form);
        return new PaginationSupport(list, totalCount, form.getiDisplayLength(), form.getiDisplayStart());
    }

    @Override
    public int remove(List<Integer> ids) {
        return this.rightsMapper.deleteByIds(ids);
    }

    @Override
    public Rights get(Integer id) {
        return this.rightsMapper.selectById(id);
    }

    @Override
    public Rights queryByName(String name) {
        return this.rightsMapper.selectByName(name);
    }

    @Override
    public int update(RightsForm form) {
        Rights rights = this.rightsMapper.selectById(form.getId());
        rights.setParentId(form.getParentId());
        rights.setName(form.getName());
        rights.setUrl(form.getUrl());
        rights.setDescription(form.getDescription());
        return this.rightsMapper.update(rights);
    }

    @Override
    public int save(RightsForm form) {
        Rights rights = new Rights();
        rights.setParentId(form.getParentId());
        rights.setName(form.getName());
        rights.setUrl(form.getUrl());
        rights.setDescription(form.getDescription());
        this.rightsMapper.insert(rights);
        return rights.getId();
    }

    @Override
    public List<RightsDto> getAllByParentId(int parentId) {

        return this.rightsMapper.selectAllByParentId(parentId);
    }

    @Override
    public List<RightsDto> getByParentId(int parentId) {
        return this.rightsMapper.selectByParentId(parentId);
    }
}
