package com.qinxiu.patterson.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qinxiu.patterson.provider.domain.Qualification;
import java.util.List;

public interface IQualificationMapper extends BaseMapper<Qualification> {

  Qualification selectLinkById(Long id);

  List<Qualification> selectAll();

  List<Qualification> selectByCourseId(Long id);

  List<Qualification> selectByStudentId(Long id);

  Qualification selectByStudentAndCourse(Long studentID, Long courseID);
}
