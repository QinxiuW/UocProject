package com.qinxiu.patterson.provider.service;

import com.qinxiu.patterson.provider.api.IQualificationService;
import com.qinxiu.patterson.provider.domain.Qualification;
import com.qinxiu.patterson.provider.mapper.IQualificationMapper;
import javax.annotation.Resource;

public class QualificationService implements IQualificationService {

  @Resource
  private IQualificationMapper qualificationMapper;

  @Override
  public int insert(Qualification qualification) {
    if (qualification == null) {
      return 0;
    }
    return qualificationMapper.insert(qualification);
  }

  @Override
  public int update(Qualification qualification) {
    if (qualification == null) {
      return 0;
    }
    if (qualificationMapper.selectById(qualification.getId()) == null) {
      return 0;
    }
    return qualificationMapper.updateById(qualification);
  }

  @Override
  public int delete(Long id) {
    if (id == null) {
      return 0;
    }
    return qualificationMapper.deleteById(id);
  }

  @Override
  public Qualification get(Long id) {
    if (id == null) {
      return null;
    }
    return qualificationMapper.selectLinkById(id);
  }
}
