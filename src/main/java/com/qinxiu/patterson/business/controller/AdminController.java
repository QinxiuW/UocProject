package com.qinxiu.patterson.business.controller;

import com.qinxiu.patterson.business.common.BusinessException;
import com.qinxiu.patterson.business.common.BusinessStatus;
import com.qinxiu.patterson.business.common.ResponseResult;
import com.qinxiu.patterson.provider.api.ICourseService;
import com.qinxiu.patterson.provider.api.IStudentService;
import com.qinxiu.patterson.provider.api.ITeacherService;
import com.qinxiu.patterson.provider.domain.Course;
import com.qinxiu.patterson.provider.domain.Student;
import com.qinxiu.patterson.provider.domain.Teacher;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "admin")
public class AdminController {

  @Resource
  ITeacherService teacherService;

  @Resource
  IStudentService studentService;

  @Resource
  ICourseService courseService;

  @PostMapping(value = "/insert/user")
  public ResponseResult<Void> insertUser(@RequestBody Student student){

     int result = studentService.insert(student);

     if (result > 0){
       return ResponseResult.<Void>builder().code(BusinessStatus.OK.getCode())
              .message(BusinessStatus.OK.getMessage()).build();
     }

     throw new BusinessException(BusinessStatus.COURSE_INSERTION_ERROR);
  }

  @PostMapping(value = "/insert/teacher")
  public ResponseResult<Void> insertTeacher(@RequestBody Teacher teacher){

    int result = teacherService.insert(teacher);

    if (result > 0){
      return ResponseResult.<Void>builder().code(BusinessStatus.OK.getCode())
          .message(BusinessStatus.OK.getMessage()).build();
    }

    throw new BusinessException(BusinessStatus.TEACHER_INSERTION_ERROR);
  }


  @PostMapping(value = "/insert/course")
  public ResponseResult<Void> insertCourse(@RequestBody Course course){

    int result = courseService.insert(course);

    if (result > 0){
      return ResponseResult.<Void>builder().code(BusinessStatus.OK.getCode())
          .message(BusinessStatus.OK.getMessage()).build();
    }

    throw new BusinessException(BusinessStatus.COURSE_INSERTION_ERROR);
  }


  // show all student

  // show all teacher

  @GetMapping(value = "/ping")
  public String ping(){
    return "pong";
  }
}
