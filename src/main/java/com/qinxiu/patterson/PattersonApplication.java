package com.qinxiu.patterson;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * PattersonApplication.
 *
 * @Description: PattersonApplication
 * @Date 27/4/24 21:48
 * @Created by qinxiuwang
 */
@SpringBootApplication
@MapperScan("com.qinxiu.patterson.provider.mapper")
public class PattersonApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(PattersonApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(PattersonApplication.class);
  }
}
