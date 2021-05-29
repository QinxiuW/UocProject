package com.qinxiu.patterson.provider.mapper.tests;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.qinxiu.patterson.provider.domain.Course;
import com.qinxiu.patterson.provider.domain.Qualification;
import com.qinxiu.patterson.provider.domain.Student;
import com.qinxiu.patterson.provider.domain.Teacher;
import com.qinxiu.patterson.provider.mapper.ICourseMapper;
import com.qinxiu.patterson.provider.mapper.IQualificationMapper;
import com.qinxiu.patterson.provider.mapper.IStudentMapper;
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
public class CourseMapperTest {

  @Resource
  private ICourseMapper courseMapper;
  @Resource
  private ITeacherMapper teacherMapper;
  @Resource
  private IQualificationMapper qualificationMapper;
  @Resource
  private IStudentMapper studentMapper;


  @BeforeEach
  public void insert() {
    // insert teacher
    Teacher teacher = Teacher.builder().name("Juan").surname("MR").build();
    Assertions.assertEquals(1, teacherMapper.insert(teacher));

    // insert course
    Course course = Course.builder().name("Math").teacherId(teacher.getId()).build();
    Assertions.assertEquals(1, courseMapper.insert(course));

    // insert student
    Student student = Student.builder().name("student").surname("student").build();
    Assertions.assertEquals(1, studentMapper.insert(student));

    // insert qualification
    Qualification qualification = Qualification.builder().score(9).courseId(course.getId())
        .studentId(student.getId()).build();
    Assertions.assertEquals(1, qualificationMapper.insert(qualification));
  }

  @Test
  void getTest() {
    //get by name
    Course course = courseMapper.selectOne(
        Wrappers.<Course>lambdaQuery().eq(Course::getName, "Math"));
    Assertions.assertNotNull(course);

    //get by id
    course = courseMapper.selectLinkById(course.getId());
    Assertions.assertNotNull(course);

    //get by id include Teacher object & Qualifications object
    course = courseMapper.selectLinkById(course.getId());
    Assertions.assertNotNull(course);
    Assertions.assertNotNull(course.getTeacher());
    Assertions.assertNotNull(course.getQualifications());

    //get all
//    var courses = courseMapper.selectList(Wrappers.<Course>lambdaQuery().select());
//    Assertions.assertNotNull(courses);
    var courses = courseMapper.selectAll();
    Assertions.assertNotNull(courses);


    //get by teacherId
    Teacher teacher = teacherMapper
        .selectOne(Wrappers.<Teacher>lambdaQuery().eq(Teacher::getName, "Juan"));
    courses = courseMapper.selectByTeacherId(teacher.getId());
    Assertions.assertNotNull(courses);
    Assertions.assertEquals("Math", courses.get(0).getName());


  }

  @Test
  void updateTest() {
    //get by name
    Course course = courseMapper.selectOne(
        Wrappers.<Course>lambdaQuery().eq(Course::getName, "Math"));
    Assertions.assertNotNull(course);


    //update the course name
    course.setName("Science");
    Assertions.assertEquals(1, courseMapper.updateById(course));

    //get the course by the new name
    course = courseMapper.selectOne(
        Wrappers.<Course>lambdaQuery().eq(Course::getName, "Science"));
    Assertions.assertNotNull(course);
  }

  @Test
  void deleteTest() {
    // get by name
    Course course = courseMapper.selectOne(
        Wrappers.<Course>lambdaQuery().eq(Course::getName, "Math"));
    Long courseId = course.getId();

    // need delete all its qualifications before, because the ForeignKey relation.
    var qualifications = qualificationMapper.selectByCourseId(courseId);
    qualifications.forEach(qualification -> {qualificationMapper.deleteById(qualification.getId());});

    // delete by id
    Assertions.assertEquals(1,  courseMapper.deleteById(courseId));

    // check if course is deleted
    course = courseMapper.selectById(courseId);
    Assertions.assertNull(course);
  }
}
