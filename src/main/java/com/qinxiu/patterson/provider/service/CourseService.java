package com.qinxiu.patterson.provider.service;

import com.qinxiu.patterson.provider.api.ICourseService;
import com.qinxiu.patterson.provider.domain.Course;
import com.qinxiu.patterson.provider.mapper.ICourseMapper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;


/**
 * CourseService.
 *
 * @Description: CourseService
 * @Date 27/4/24 21:46
 * @Created by qinxiuwang
 */
@Service
public class CourseService implements ICourseService {

  @Resource
  private ICourseMapper courseMapper;

  @Override
  public int insert(Course course) {
    if (course == null) {
      return 0;
    }
    return courseMapper.insert(course);
  }

  @Override
  public int update(Course course) {
    if (course == null) {
      return 0;
    }
    if (courseMapper.selectLinkById(course.getId()) == null) {
      return 0;
    }
    return courseMapper.updateById(course);
  }

  @Override
  public int delete(Long id) {
    if (id == null) {
      return 0;
    }
    return courseMapper.deleteById(id);
  }

  @Override
  public Course get(Long id) {
    if (id == null) {
      return null;
    }
    return courseMapper.selectLinkById(id);
  }

  @Override
  public List<Course> getAll() {
    return courseMapper.selectAll();
  }

  @Override
  public List<Course> getCourseByTeacherId(Long teacherId) {
    if (teacherId == null) {
      return null;
    }
    return courseMapper.selectByTeacherId(teacherId);
  }
}
