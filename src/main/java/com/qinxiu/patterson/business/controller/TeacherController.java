package com.qinxiu.patterson.business.controller;

import com.qinxiu.patterson.business.common.BusinessException;
import com.qinxiu.patterson.business.common.BusinessStatus;
import com.qinxiu.patterson.business.common.ResponseResult;
import com.qinxiu.patterson.provider.api.ICourseService;
import com.qinxiu.patterson.provider.api.IQualificationService;
import com.qinxiu.patterson.provider.api.ITeacherService;
import com.qinxiu.patterson.provider.domain.Qualification;
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
    if (course ==null){
      throw new BusinessException(BusinessStatus.COURSE_NOT_FOUND);
    }

    if(course.getTeacherId() != null){
      throw new BusinessException(BusinessStatus.COURSE_APPLIED);
    }

    course.setTeacherId(teacherID);

    var result = courseService.update(course);
    if(result>0){
      return ResponseResult.<Void>builder().code(BusinessStatus.OK.getCode())
          .message(BusinessStatus.OK.getMessage()).build();
    }
    throw new BusinessException(BusinessStatus.COURSE_APPLICATION_ERROR);
  }



  @GetMapping(value = "qualifications")
  public ResponseResult<Map<String, List<Qualification>>> getQualifications(
      @RequestParam Long teacherID) {

    // find courses
    var courses = courseService.getCourseByTeacherId(teacherID);

    if (courses == null) {
      throw new BusinessException(BusinessStatus.COURSE_NOT_FOUND);
    }
    // loop the courses and find its qualifications
    Map<String, List<Qualification>> qualificationsMap = new HashMap<>();

    for (var course : courses) {
      var qualifications = qualificationService.getQualificationsByCourse(course.getId());
      if (!qualificationsMap.containsKey(course.getName())) {
        qualificationsMap.put(course.getName(), qualifications);
      }
    }
    return ResponseResult.<Map<String, List<Qualification>>>builder()
        .message(BusinessStatus.OK.getMessage())
        .code(BusinessStatus.OK.getCode())
        .data(qualificationsMap).build();
  }

  @GetMapping(value = "getQualifications")
  public ResponseResult<List<Qualification>> getQualifications(@RequestParam Long teacherID, @RequestParam Long courseID){

    // check if this teacher has applied to this course
    var courses = courseService.getCourseByTeacherId(teacherID);
    boolean isApplied = false;
    for(var course : courses){
      if(course.getId().equals(courseID)) {
        isApplied = true;
        break;
      }
    }

    if(!isApplied){
      throw new BusinessException(BusinessStatus.COURSE_PERMISSION_DENIED);
    }
    var qualifications = qualificationService.getQualificationsByCourse(courseID);
    return ResponseResult.<List<Qualification>>builder()
        .message(BusinessStatus.OK.getMessage())
        .code(BusinessStatus.OK.getCode())
        .data(qualifications).build();
  }

  @PostMapping(value = "markQualification")
  public ResponseResult<Void> markQualification(@RequestParam Long courseID, @RequestParam Long studentID,@RequestParam int score){

    // find the corresponding qualification
    var qualification = qualificationService.get(studentID,courseID);
    if(qualification == null){
      throw new BusinessException(BusinessStatus.QUALIFICATION_NOT_FOUND);
    }
    // update the score
    qualification.setScore(score);

    var result = qualificationService.update(qualification);
    if (result > 0){
      return ResponseResult.<Void>builder().code(BusinessStatus.OK.getCode())
          .message(BusinessStatus.OK.getMessage()).build();
    }
    throw new BusinessException(BusinessStatus.QUALIFICATION_MARK_FAILED);
  }





  @GetMapping(value = "/ping")
  public String ping(){
    return "pong";
  }
}
