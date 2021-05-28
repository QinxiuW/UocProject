package com.qinxiu.patterson.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qinxiu.patterson.provider.domain.Qualification;
import java.util.List;
import org.apache.ibatis.annotations.Select;

public interface IQualificationMapper extends BaseMapper<Qualification> {

  Qualification selectLinkById(Long id);

  @Select("select * from tb_qualification where course_id = #{id}")
  List<Qualification> selectByCourseId(Long id);

  @Select("select * from tb_qualification where student_id = #{id}")
  List<Qualification> selectByStudentId(Long id);

}
