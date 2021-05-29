package com.qinxiu.patterson.business.common;

public enum BusinessStatus {
  UNKNOWN(-1, "UNKNOWN"),
  OK(2000, "Request successful"),

  //=========================================================================================
  //                1001 - 1010 -> Student status code
  //=========================================================================================
  STUDENT_INSERTION_ERROR(1001, "Student insertion error"),
  STUDENT_NOT_FOUND(1002, "Student not found"),

  //=========================================================================================
  //                1011 - 1020 -> Teacher status code
  //=========================================================================================
  TEACHER_INSERTION_ERROR(400001, "Teacher insertion error"),
  TEACHER_NOT_FOUND(400001, "Teacher not found"),

  //=========================================================================================
  //                1021 - 1030 -> Course status code
  //=========================================================================================
  COURSE_APPLICATION_ERROR(100001, "Course application error"),
  COURSE_INSERTION_ERROR(500001, "Course insertion error"),
  COURSE_NOT_FOUND(500002, "Course not found"),
  COURSE_APPLIED(500002,"This course has been already applied"),
  COURSE_PERMISSION_DENIED(500002, "Course permission denied"),

  //=========================================================================================
  //                1031 - 1040 -> Qualification status code
  //=========================================================================================
  QUALIFICATION_NOT_FOUND(500002, "Qualification not found"),
  QUALIFICATION_MARK_FAILED(500002, "Qualification mark failed");

  private final Integer code;
  private final String message;

  BusinessStatus(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public Integer getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  /**
   * Get exception info by code.
   *
   * @param code {@code int}
   * @return {@code String}
   */
  public static String getMessage(int code) {
    for (BusinessStatus status : values()) {
      if (status.getCode().equals(code)) {
        return status.getMessage();
      }
    }
    return null;
  }

}
