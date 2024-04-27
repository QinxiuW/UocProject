package com.qinxiu.patterson.business.controller;


import com.qinxiu.patterson.business.common.BusinessException;
import com.qinxiu.patterson.business.common.BusinessStatus;
import com.qinxiu.patterson.business.common.ResponseResult;
import com.qinxiu.patterson.provider.api.IQualificationService;
import com.qinxiu.patterson.provider.api.IStudentService;
import com.qinxiu.patterson.provider.domain.Qualification;
import com.qinxiu.patterson.provider.domain.Student;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * StudentController.
 *
 * @Description: StudentController
 * @Date 27/4/24 21:53
 * @Created by qinxiuwang
 */
@RestController
@RequestMapping(value = "student")
public class StudentController {

  @Resource
  private IQualificationService qualificationService;

  @Resource
  private IStudentService studentService;

  /**
   * Apply a student to a subject/course.
   *
   * @param studentId {@code Long}
   * @param courseId  {@code Long}
   * @return {@link ResponseResult}
   */
  @PostMapping(value = "apply/course")
  public ResponseResult<Void> applyCourse(@RequestParam Long studentId,
      @RequestParam Long courseId) {

    var qualification = Qualification.builder().courseId(courseId).studentId(studentId).build();
    var result = qualificationService.insert(qualification);
    if (result > 0) {
      return ResponseResult.<Void>builder().code(BusinessStatus.OK.getCode())
          .message(BusinessStatus.OK.getMessage()).build();
    }
    throw new BusinessException(BusinessStatus.COURSE_APPLICATION_ERROR);
  }

  /**
   * Get the corresponding qualifications by a student.
   *
   * @param studentId {@code Long}
   * @return {@link ResponseResult}
   */
  @GetMapping(value = "qualifications")
  public ResponseResult<List<Qualification>> getQualifications(@RequestParam Long studentId) {

    var student = studentService.get(studentId);
    if (student == null) {
      throw new BusinessException(BusinessStatus.STUDENT_NOT_FOUND);
    }
    return ResponseResult.<List<Qualification>>builder().message(BusinessStatus.OK.getMessage())
        .code(BusinessStatus.OK.getCode()).data(student.getQualifications()).build();
  }

  /**
   * Get the corresponding student by the Id.
   *
   * @param studentId {@code Long}
   * @return {@link ResponseResult}
   */
  @GetMapping(value = "")
  public ResponseResult<Student> get(@RequestParam Long studentId) {

    var student = studentService.get(studentId);
    if (student == null) {
      throw new BusinessException(BusinessStatus.STUDENT_NOT_FOUND);
    }
    return ResponseResult.<Student>builder().message(BusinessStatus.OK.getMessage())
        .code(BusinessStatus.OK.getCode()).data(student).build();
  }

  /**
   * Ping action for health check.
   *
   * @return {@code String}
   */
  @GetMapping(value = "/ping")
  public String ping() {
    return "studentController pong";
  }
}
