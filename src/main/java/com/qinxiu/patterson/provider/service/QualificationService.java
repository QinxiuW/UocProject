package com.qinxiu.patterson.provider.service;

import com.qinxiu.patterson.provider.api.IQualificationService;
import com.qinxiu.patterson.provider.domain.Qualification;
import com.qinxiu.patterson.provider.mapper.IQualificationMapper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * QualificationService.
 *
 * @Description: QualificationService
 * @Date 27/4/24 21:48
 * @Created by qinxiuwang
 */
@Service
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
    if (qualificationMapper.selectLinkById(qualification.getId()) == null) {
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

  @Override
  public Qualification get(Long studentId, Long courseId) {
    if (courseId == null || studentId == null) {
      return null;
    }
    return qualificationMapper.selectByStudentAndCourse(studentId, courseId);
  }

  @Override
  public List<Qualification> getByCourseId(Long courseId) {
    if (courseId == null) {
      return null;
    }
    return qualificationMapper.selectByCourseId(courseId);
  }

}
