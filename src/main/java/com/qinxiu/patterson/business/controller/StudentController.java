package com.qinxiu.patterson.business.controller;


import com.qinxiu.patterson.business.common.BusinessException;
import com.qinxiu.patterson.business.common.BusinessStatus;
import com.qinxiu.patterson.business.common.ResponseResult;
import com.qinxiu.patterson.provider.api.IQualificationService;
import com.qinxiu.patterson.provider.api.IStudentService;
import com.qinxiu.patterson.provider.domain.Qualification;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "student")
public class StudentController {

  @Resource
  IQualificationService qualificationService;

  @Resource
  IStudentService studentService;

  @PostMapping(value = "apply/course")
  public ResponseResult<Void> applyCourse(@RequestParam Long studentID,
      @RequestParam Long courseID) {

    var qualification = Qualification.builder().courseId(courseID).studentId(studentID).build();
    var result = qualificationService.insert(qualification);
    if (result > 0) {
      return ResponseResult.<Void>builder().code(BusinessStatus.OK.getCode())
          .message(BusinessStatus.OK.getMessage()).build();
    }
    throw new BusinessException(BusinessStatus.COURSE_APPLICATION_ERROR);
  }

  @GetMapping(value = "qualifications")
  public ResponseResult<List<Qualification>> getQualifications(@RequestParam Long studentID) {

    var student = studentService.get(studentID);
    if (student == null) {
      throw new BusinessException(BusinessStatus.STUDENT_NOT_FOUND);
    }
    return ResponseResult.<List<Qualification>>builder()
        .message(BusinessStatus.OK.getMessage())
        .code(BusinessStatus.OK.getCode())
        .data(student.getQualifications()).build();
  }

  @GetMapping(value = "/ping")
  public String ping() {
    return "studentController pong";
  }
}
