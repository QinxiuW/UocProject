package com.qinxiu.patterson.business.common;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * BusinessExceptionHandler.
 *
 * @Description: BusinessExceptionHandler
 * @Date 27/4/24 21:46
 * @Created by qinxiuwang
 */
@ControllerAdvice
public class BusinessExceptionHandler {

  /**
   * Exception handler.
   *
   * @param request {@code HttpServletRequest}
   * @param ex      {@code Exception}
   * @return {@code ResponseEntity}
   */
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<?> handlerException(HttpServletRequest request, Exception ex) {
    ResponseResult<Void> error = ResponseResult.<Void>builder().build();

    // business exception
    if (ex instanceof BusinessException) {
      error.setCode(((BusinessException) ex).getCode());
      error.setMessage(ex.getMessage());

      // unknown error
    } else {
      error.setCode(BusinessStatus.UNKNOWN.getCode());
      error.setMessage(BusinessStatus.UNKNOWN.getMessage());
    }

    return new ResponseEntity<>(error, HttpStatus.OK);
  }

}
