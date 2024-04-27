package com.qinxiu.patterson.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qinxiu.patterson.provider.domain.Teacher;
import java.util.List;

/**
 * ITeacherMapper.
 *
 * @Description: ITeacherMapper
 * @Date 27/4/24 21:53
 * @Created by qinxiuwang
 */
public interface ITeacherMapper extends BaseMapper<Teacher> {

  Teacher selectLinkById(Long id);

  List<Teacher> selectAll();

}
