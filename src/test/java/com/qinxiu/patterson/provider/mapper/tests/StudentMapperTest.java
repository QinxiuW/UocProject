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
public class StudentMapperTest {

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
    // get by name and surname
    var student = studentMapper.selectOne(
        Wrappers.<Student>lambdaQuery().eq(Student::getName, "studentName")
            .eq(Student::getSurname, "studentSurname"));
    Assertions.assertNotNull(student);

    // get by id
    student = studentMapper.selectLinkById(student.getId());
    Assertions.assertNotNull(student);

    // get by id include Qualifications object
    student = studentMapper.selectLinkById(student.getId());
    Assertions.assertNotNull(student);
    Assertions.assertNotNull(student.getQualifications());

    // get all
    var students = studentMapper.selectAll();
    Assertions.assertNotNull(students);
  }

  @Test
  void updateTest() {
    // get by name and surname
    var student = studentMapper.selectOne(
        Wrappers.<Student>lambdaQuery().eq(Student::getName, "studentName")
            .eq(Student::getSurname, "studentSurname"));

    // update the student
    student.setName("Juanito");
    student.setSurname("Furano");
    Assertions.assertEquals(1, studentMapper.updateById(student));

    // check the updated student
    student = studentMapper.selectOne(
        Wrappers.<Student>lambdaQuery().eq(Student::getName, "Juanito")
            .eq(Student::getSurname, "Furano"));
    Assertions.assertNotNull(student);
  }

  @Test
  void deleteTest() {
    // get by name and surname
    var student = studentMapper.selectOne(
        Wrappers.<Student>lambdaQuery().eq(Student::getName, "studentName")
            .eq(Student::getSurname, "studentSurname"));
    Long studentId = student.getId();

    // need delete all its qualifications before, because the ForeignKey relation.
    var qualifications = qualificationMapper.selectByStudentId(studentId);
    qualifications.forEach(qualification -> {
      qualificationMapper.deleteById(qualification.getId());
    });

    // delete by id
    Assertions.assertEquals(1, studentMapper.deleteById(studentId));

    // check if still exists the deleted student
    student = studentMapper.selectById(studentId);
    Assertions.assertNull(student);
  }
}
