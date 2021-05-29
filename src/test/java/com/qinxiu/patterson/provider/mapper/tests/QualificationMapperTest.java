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
public class QualificationMapperTest {

  @Resource
  private ICourseMapper courseMapper;
  @Resource
  private ITeacherMapper teacherMapper;
  @Resource
  private IQualificationMapper qualificationMapper;
  @Resource
  private IStudentMapper studentMapper;

  @BeforeEach
  void insert() {

    // insert course
    Teacher teacher = Teacher.builder().name("Juan").surname("MR").build();
    Assertions.assertEquals(1, teacherMapper.insert(teacher));

    Course course = Course.builder().name("Math").teacherId(teacher.getId()).build();
    Assertions.assertEquals(1, courseMapper.insert(course));

    // insert student
    Student student = Student.builder().name("studentName").surname("studentSurname").build();
    Assertions.assertEquals(1, studentMapper.insert(student));

    // insert qualification
    Qualification qualification = Qualification.builder().score(9).courseId(course.getId())
        .studentId(student.getId()).build();
    Assertions.assertEquals(1, qualificationMapper.insert(qualification));
  }

  @Test
  void getTest() {
    // get by score
    var qualification = qualificationMapper
        .selectOne(Wrappers.<Qualification>lambdaQuery().eq(Qualification::getScore, 9));
    Assertions.assertNotNull(qualification);
//    Assertions.assertNull(qualification.getCourse());

    // get by id
    qualification = qualificationMapper.selectById(qualification.getId());
    Assertions.assertNotNull(qualification);
//    Assertions.assertNull(qualification.getCourse());

//    // get by id include Course object & Student object
//    qualification = qualificationMapper.selectLinkById(qualification.getId());
//    Assertions.assertNotNull(qualification);
//    Assertions.assertNotNull(qualification.getCourse());
//    Assertions.assertNotNull(qualification.getStudent());

    // get all
    var qualifications = qualificationMapper
        .selectList(Wrappers.<Qualification>lambdaQuery().select());
    Assertions.assertNotNull(qualifications);

    // get by courseId
    Course course = courseMapper.selectOne(
        Wrappers.<Course>lambdaQuery().eq(Course::getName, "Math"));
    qualifications = qualificationMapper.selectByCourse(course.getId());
    Assertions.assertNotNull(qualifications);

    // get by studentId
    Student student = studentMapper.selectOne(
        Wrappers.<Student>lambdaQuery().eq(Student::getSurname, "studentSurname"));
    qualifications = qualificationMapper.selectByStudent(student.getId());
    Assertions.assertNotNull(qualifications);
  }

  @Test
  void updateTest() {
    // get by score
    var qualification = qualificationMapper
        .selectOne(Wrappers.<Qualification>lambdaQuery().eq(Qualification::getScore, 9));

    // update the score
    qualification.setScore(1);
    Assertions.assertEquals(1, qualificationMapper.updateById(qualification));

    // get the qualification by the new score
    qualification = qualificationMapper
        .selectOne(Wrappers.<Qualification>lambdaQuery().eq(Qualification::getScore, 1));
    Assertions.assertNotNull(qualification);
  }

  @Test
  void deleteTest() {
    // get by score
    var qualification = qualificationMapper
        .selectOne(Wrappers.<Qualification>lambdaQuery().eq(Qualification::getScore, 9));
    Long qualificationId = qualification.getId();
    Assertions.assertEquals(1,qualificationMapper.deleteById(qualificationId));

    // delete by id
    qualification = qualificationMapper.selectById(qualificationId);
    Assertions.assertNull(qualification);
  }


}
