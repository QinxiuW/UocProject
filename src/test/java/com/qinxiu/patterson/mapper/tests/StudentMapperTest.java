package com.qinxiu.patterson.mapper.tests;

import com.qinxiu.patterson.domain.Student;
import com.qinxiu.patterson.mapper.IStudentMapper;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentMapperTest {

  @Resource
  private IStudentMapper studentMapper;

  @Test
  public void studentTest() {

    System.out.println("start");
    studentMapper.insert(Student.builder().name("student").firstName("first").build());
    List<Student> students = studentMapper.selectList(null);
    students.forEach(System.out::println);
  }
}
