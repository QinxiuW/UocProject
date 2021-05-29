package com.qinxiu.patterson.provider.api;


import com.qinxiu.patterson.provider.domain.Qualification;

public interface IQualificationService {

  int insert(Qualification qualification);

  int update(Qualification qualification);

  int delete(Long id);

  Qualification get(Long id);

  Qualification get(Long studentId,Long courseId);

}
