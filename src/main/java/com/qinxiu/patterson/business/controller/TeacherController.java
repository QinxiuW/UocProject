package com.qinxiu.patterson.business.controller;

import com.qinxiu.patterson.business.common.BusinessException;
import com.qinxiu.patterson.business.common.BusinessStatus;
import com.qinxiu.patterson.business.common.ResponseResult;
import com.qinxiu.patterson.provider.api.ICourseService;
import com.qinxiu.patterson.provider.api.IQualificationService;
import com.qinxiu.patterson.provider.api.ITeacherService;
import com.qinxiu.patterson.provider.domain.Course;
import com.qinxiu.patterson.provider.domain.Qualification;
import com.qinxiu.patterson.provider.domain.Teacher;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "teacher")
public class TeacherController {

  @Resource
  ITeacherService teacherService;

  @Resource
  ICourseService courseService;

  @Resource
  IQualificationService qualificationService;


  @PostMapping(value = "apply/course")
  public ResponseResult<Void> applyCourse(@RequestParam Long teacherID,
      @RequestParam Long courseID) {

    var course = courseService.get(courseID);
    if (course == null) {
      throw new BusinessException(BusinessStatus.COURSE_NOT_FOUND);
    }

    if (course.getTeacherId() != null) {
      throw new BusinessException(BusinessStatus.COURSE_APPLIED);
    }

    course.setTeacherId(teacherID);

    var result = courseService.update(course);
    if (result > 0) {
      return ResponseResult.<Void>builder().code(BusinessStatus.OK.getCode())
          .message(BusinessStatus.OK.getMessage()).build();
    }
    throw new BusinessException(BusinessStatus.COURSE_APPLICATION_ERROR);
  }

  @GetMapping(value = "")
  public ResponseResult<Teacher> get(@RequestParam Long teacherID) {

    // find teacher
    var teacher = teacherService.get(teacherID);
    if (teacher == null) {
      throw new BusinessException(BusinessStatus.TEACHER_NOT_FOUND);
    }

    return ResponseResult.<Teacher>builder()
        .message(BusinessStatus.OK.getMessage())
        .code(BusinessStatus.OK.getCode())
        .data(teacher).build();
  }

  @GetMapping(value = "qualifications")
  public ResponseResult<Map<String, List<Qualification>>> getQualifications(
      @RequestParam Long teacherID) {

    // find teacher
    var teacher = teacherService.get(teacherID);
    if (teacher == null) {
      throw new BusinessException(BusinessStatus.TEACHER_NOT_FOUND);
    }

    // loop the courses and find its qualifications
    Map<String, List<Qualification>> qualificationsMap = new HashMap<>();

    for (Course course : teacher.getCourses()) {
     // find qualifications
      var qualificaitons = qualificationService.getByCourseId(course.getId());
      if (!qualificationsMap.containsKey(course.getName())) {
        qualificationsMap.put(course.getName(), qualificaitons);
      }
    }
    return ResponseResult.<Map<String, List<Qualification>>>builder()
        .message(BusinessStatus.OK.getMessage())
        .code(BusinessStatus.OK.getCode())
        .data(qualificationsMap).build();
  }

  @GetMapping(value = "qualificationsByCourse")
  public ResponseResult<List<Qualification>> getQualifications(@RequestParam Long teacherID,
      @RequestParam Long courseID) {

    // find teacher
    var teacher = teacherService.get(teacherID);
    if (teacher == null) {
      throw new BusinessException(BusinessStatus.TEACHER_NOT_FOUND);
    }

    // find the corresponding course
    Course courseRS = null;
    for (var course : teacher.getCourses()) {
      if (course.getId() == courseID) {
        courseRS = course;
      }
    }
    if(courseRS == null){
      throw new BusinessException(BusinessStatus.COURSE_PERMISSION_DENIED);
    }

    var qualificaitons = qualificationService.getByCourseId(courseID);

    return ResponseResult.<List<Qualification>>builder()
        .message(BusinessStatus.OK.getMessage())
        .code(BusinessStatus.OK.getCode())
        .data(qualificaitons).build();
  }

  @GetMapping(value = "getCourses")
  public ResponseResult<List<Course>> getCourses(@RequestParam Long teacherID) {

    // find teacher
    var teacher = teacherService.get(teacherID);
    if (teacher == null) {
      throw new BusinessException(BusinessStatus.TEACHER_NOT_FOUND);
    }
    return ResponseResult.<List<Course>>builder()
        .message(BusinessStatus.OK.getMessage())
        .code(BusinessStatus.OK.getCode())
        .data(teacher.getCourses()).build();
  }


  @PostMapping(value = "markQualification")
  public ResponseResult<Void> markQualification(@RequestParam Long courseID,
      @RequestParam Long studentID, @RequestParam int score) {

    // find the corresponding qualification
    var qualification = qualificationService.get(studentID, courseID);
    if (qualification == null) {
      throw new BusinessException(BusinessStatus.QUALIFICATION_NOT_FOUND);
    }
    // update the score
    qualification.setScore(score);

    var result = qualificationService.update(qualification);
    if (result > 0) {
      return ResponseResult.<Void>builder().code(BusinessStatus.OK.getCode())
          .message(BusinessStatus.OK.getMessage()).build();
    }
    throw new BusinessException(BusinessStatus.QUALIFICATION_MARK_FAILED);
  }

  @GetMapping(value = "/ping")
  public String ping() {
    return "teacherController pong";
  }
}
