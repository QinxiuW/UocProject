package com.qinxiu.patterson.business.common;

/**
 * BusinessException.
 *
 * @Description: BusinessException
 * @Date 27/4/24 21:45
 * @Created by qinxiuwang
 */
public class BusinessException extends RuntimeException {

  private static final long serialVersionUID = 8271010638103209312L;

  private Integer code;

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public BusinessException() {
  }

  public BusinessException(BusinessStatus status) {
    super(status.getMessage());
    this.code = status.getCode();
  }
}
