package com.qinxiu.patterson.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qinxiu.patterson.provider.domain.Student;
import java.util.List;


/**
 * IStudentMapper.
 *
 * @Description: IStudentMapper
 * @Date 27/4/24 21:54
 * @Created by qinxiuwang
 */
public interface IStudentMapper extends BaseMapper<Student> {

  Student selectLinkById(Long id);

  List<Student> selectAll();
}
