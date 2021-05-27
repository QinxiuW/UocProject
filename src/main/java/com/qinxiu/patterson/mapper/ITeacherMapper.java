package com.qinxiu.patterson.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qinxiu.patterson.domain.Teacher;

public interface ITeacherMapper extends BaseMapper<Teacher> {

  Teacher selectLinkById(Long id);
}
