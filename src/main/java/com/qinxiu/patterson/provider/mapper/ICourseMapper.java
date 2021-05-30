package com.qinxiu.patterson.provider.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qinxiu.patterson.provider.domain.Course;
import java.util.List;

public interface ICourseMapper extends BaseMapper<Course> {

  Course selectLinkById(Long id);

  List<Course> selectByTeacherId(Long id);

  List<Course> selectAll();
}
