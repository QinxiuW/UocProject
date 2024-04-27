package com.qinxiu.patterson.view;

import com.qinxiu.patterson.provider.api.ICourseService;
import com.qinxiu.patterson.provider.api.IQualificationService;
import com.qinxiu.patterson.provider.api.IStudentService;
import com.qinxiu.patterson.provider.api.ITeacherService;
import com.qinxiu.patterson.provider.domain.Course;
import com.qinxiu.patterson.provider.domain.Qualification;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * ThymeleafController.
 *
 * @Description: ThymeleafController
 * @Date 27/4/24 21:52
 * @Created by qinxiuwang
 */
@Controller
public class ThymeleafController {

  @Resource
  IStudentService studentService;

  @Resource
  ITeacherService teacherService;

  @Resource
  ICourseService courseService;

  @Resource
  IQualificationService qualificationService;

  /**
   * ThymeleafController.
   *
   * @Description: ThymeleafController
   * @Date 27/4/24 21:56
   * @Created by qinxiuwang
   */
  @Data
  public class Form {

    public Long studentId;
    public Long courseId;
    public Long teacherId;
  }

  /**
   * Thymeleaf controller corresponds to the index.html page.
   *
   * @param model {@link Model}
   * @return {@code String}
   */
  @GetMapping("/index")
  public String main(Model model) {
    var students = studentService.getAll();
    model.addAttribute("studentList", students);

    // all courses
    var courses = courseService.getAll();
    model.addAttribute("courseList", courses);

    // get qualifications by teacher
    var teachers = teacherService.getAll();
    model.addAttribute("teacherList", teachers);
    model.addAttribute("mainForm", new Form());
    return "index";
  }

  /**
   * Thymeleaf controller corresponds to the studentResult.html. Show the qualifications result of
   * corresponding Student.
   *
   * @param form  {@link Form}
   * @param model {@link Model}
   * @return {@code String}
   */
  @PostMapping("/showByStudent")
  public String showByStudent(Form form, Model model) {
    List<Qualification> qualifications = new ArrayList<>();
    var studentObj = studentService.get(form.studentId);
    // all courses
    if (form.courseId == -1) {

      qualifications = studentObj.getQualifications();
      // specific student & course
    } else {
      qualifications.add(qualificationService.get(form.studentId, form.courseId));
    }

    model.addAttribute("student", studentObj);
    model.addAttribute("qualificationList", qualifications);
    return "studentResult";
  }

  /**
   * Thymeleaf controller corresponds to the teacherResult.html. Show the qualifications result of
   * corresponding Teacher.
   *
   * @param form  {@link Form}
   * @param model {@link Model}
   * @return {@link Model}
   */
  @PostMapping("/showByTeacher")
  public String showByTeacher(Form form, Model model) {
    Map<String, List<Qualification>> qualificationsMap = new HashMap<>();

    // load all qualification into a HasMap.
    var teacherObj = teacherService.get(form.teacherId);
    for (Course course : teacherObj.getCourses()) {
      // find qualifications
      var qualificaitons = qualificationService.getByCourseId(course.getId());
      if (!qualificationsMap.containsKey(course.getName())) {
        qualificationsMap.put(course.getName(), qualificaitons);
      }
    }

    //specific course
    if (form.courseId > -1) {
      var course = courseService.get(form.courseId);
      var qualifications = qualificationsMap.get(course.getName());
      qualificationsMap = new HashMap<>();
      qualificationsMap.put(course.getName(), qualifications);
    }

    model.addAttribute("teacher", teacherObj);
    model.addAttribute("qualificationHashMap", qualificationsMap);
    return "teacherResult";
  }
}
