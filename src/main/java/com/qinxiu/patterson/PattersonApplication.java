package com.qinxiu.patterson;

import com.qinxiu.patterson.business.api.IAdminAction;
import com.qinxiu.patterson.business.api.IStudentAction;
import com.qinxiu.patterson.business.api.ITeacherAction;
import com.qinxiu.patterson.business.imp.AdminAction;
import com.qinxiu.patterson.provider.api.ICourseService;
import com.qinxiu.patterson.provider.domain.Course;
import com.qinxiu.patterson.provider.domain.Teacher;
import com.qinxiu.patterson.provider.mapper.ITeacherMapper;
import com.qinxiu.patterson.provider.service.CourseService;
import com.qinxiu.patterson.view.Console;
import javax.annotation.Resource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@MapperScan("com.qinxiu.patterson.provider.mapper")
public class PattersonApplication {


  public static void main(String[] args) {

    var applicationContext = SpringApplication.run(PattersonApplication.class, args);



    var adminActionBean = applicationContext.getBean(IAdminAction.class);
    var studentActionBean =applicationContext.getBean(IStudentAction.class);
    var teacherActionBean =applicationContext.getBean(ITeacherAction.class);

    Console console = new Console(adminActionBean,studentActionBean,teacherActionBean);
    console.adminProcess();
    console.showAll();
//    var b = a.insert(Course.builder().name("Math").build());
//    System.out.println(b);
  }

}
