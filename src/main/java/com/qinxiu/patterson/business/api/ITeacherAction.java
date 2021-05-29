package com.qinxiu.patterson.business.api;

import java.util.List;

public interface ITeacherAction {

    String getAllQualificationsByCourse(Long teacherId, String courseName);

    String getAllQualificationsByCourses(Long teacherId, List<String> courseNames);

    int setQualificationToStudent(Long studentId,String courseName,int score);

}

