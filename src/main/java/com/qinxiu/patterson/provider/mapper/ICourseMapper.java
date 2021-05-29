package com.qinxiu.patterson.provider.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qinxiu.patterson.provider.domain.Course;
import java.util.List;
import org.apache.ibatis.annotations.Select;

public interface ICourseMapper extends BaseMapper<Course> {

  Course selectLinkById(Long id);

  List<Course> selectByTeacherId(Long id);

  List<Course> selectAll();
}
