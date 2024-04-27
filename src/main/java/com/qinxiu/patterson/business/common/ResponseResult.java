package com.qinxiu.patterson.business.common;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * ResponseResult.
 *
 * @Description: ResponseResult
 * @Date 27/4/24 21:48
 * @Created by qinxiuwang
 */
@Data
@ToString
@EqualsAndHashCode
@Builder
public class ResponseResult<T> implements Serializable {


  private static final long serialVersionUID = -6029291628888865543L;

  private Integer code;

  private String message;

  private T data;
}
