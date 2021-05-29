package com.qinxiu.patterson.provider.mapper.tests;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.qinxiu.patterson.provider.domain.Course;
import com.qinxiu.patterson.provider.domain.Teacher;
import com.qinxiu.patterson.provider.mapper.ICourseMapper;
import com.qinxiu.patterson.provider.mapper.ITeacherMapper;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback
public class TeacherMapperTest {

  @Resource
  private ITeacherMapper teacherMapper;

  @Resource
  private ICourseMapper courseMapper;

  @BeforeEach
  void insert() {
    // insert teacher
    Teacher teacher = Teacher.builder().name("Juan").surname("MR").build();
    Assertions.assertEquals(1, teacherMapper.insert(teacher));

    // insert course
    Course course = Course.builder().name("Math").teacherId(teacher.getId()).build();
    Assertions.assertEquals(1, courseMapper.insert(course));
  }

  @Test
  void getTest() {
    // get by name and surname
    var teacher = teacherMapper.selectOne(
        Wrappers.<Teacher>lambdaQuery().eq(Teacher::getName, "Juan")
            .eq(Teacher::getSurname, "MR"));
    Assertions.assertNotNull(teacher);

    // get by id
    teacher = teacherMapper.selectLinkById(teacher.getId());
    Assertions.assertNotNull(teacher);

    // get by id include Score object
    teacher = teacherMapper.selectLinkById(teacher.getId());
    Assertions.assertNotNull(teacher);
    Assertions.assertNotNull(teacher.getCourses());

    // get all
    var teachers = teacherMapper.selectAll();
    Assertions.assertNotNull(teachers);


  }

  @Test
  void updateTest() {
    // get by name and surname
    var teacher = teacherMapper.selectOne(
        Wrappers.<Teacher>lambdaQuery().eq(Teacher::getName, "Juan")
            .eq(Teacher::getSurname, "MR"));
    Long teacherId = teacher.getId();

    // update the teacher
    teacher.setName("teacherName");
    teacher.setSurname("teacherSurname");
    Assertions.assertEquals(1, teacherMapper.updateById(teacher));

    // check the updated teacher
    teacher = teacherMapper.selectOne(
        Wrappers.<Teacher>lambdaQuery().eq(Teacher::getName, "teacherName")
            .eq(Teacher::getSurname, "teacherSurname"));
    Assertions.assertNotNull(teacher);
  }

  @Test
  void deleteTest() {
    // get by name and surname
    var teacher = teacherMapper.selectOne(
        Wrappers.<Teacher>lambdaQuery().eq(Teacher::getName, "Juan")
            .eq(Teacher::getSurname, "MR"));
    Long teacherId = teacher.getId();

    // need delete all its courses before, because the ForeignKey relation.
    var courses = courseMapper.selectByTeacherId(teacherId);
    courses.forEach(course -> {
      courseMapper.deleteById(course.getId());
    });

    // delete by id
    Assertions.assertEquals(1, teacherMapper.deleteById(teacherId));

    // check if still exists the deleted teacher
    teacher = teacherMapper.selectById(teacherId);
    Assertions.assertNull(teacher);
  }

}
