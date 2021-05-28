package com.qinxiu.patterson.provider.service.tests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import com.qinxiu.patterson.provider.domain.Teacher;
import com.qinxiu.patterson.provider.mapper.ITeacherMapper;
import com.qinxiu.patterson.provider.service.TeacherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {

  @Mock
  ITeacherMapper teacherMapper;

  @Mock
  Teacher mockTeacher;

  @InjectMocks
  TeacherService teacherService;

  @Test
  void insert_success() {
    // Arrange
    Mockito.when(teacherMapper.insert(any(Teacher.class))).thenReturn(1);

    // Act
    var result = teacherService.insert(mockTeacher);

    // Assert
    Assertions.assertEquals(1, result);
    Mockito.verify(teacherMapper, Mockito.times(1)).insert(any(Teacher.class));
  }

  @Test
  void insert_failed_by_empty_param() {
    // Arrange

    // Act
    var result = teacherService.insert(null);

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(teacherMapper, Mockito.times(0)).insert(any(Teacher.class));
  }

  @Test
  void insert_failed_by_return_value() {
    // Arrange
    Mockito.when(teacherMapper.insert(any(Teacher.class))).thenReturn(0);

    // Act
    var result = teacherService.insert(mockTeacher);

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(teacherMapper, Mockito.times(1)).insert(any(Teacher.class));
  }

  @Test
  void update_success() {
    // Arrange
    Mockito.when(teacherMapper.selectById(anyLong())).thenReturn(mockTeacher);
    Mockito.when(teacherMapper.updateById(any(Teacher.class))).thenReturn(1);

    // Act
    var result = teacherService.update(mockTeacher);

    // Assert
    Assertions.assertEquals(1, result);
    Mockito.verify(teacherMapper, Mockito.times(1)).selectById(anyLong());
    Mockito.verify(teacherMapper, Mockito.times(1)).updateById(any(Teacher.class));
  }

  @Test
  void update_failed_by_empty_param() {
    // Arrange

    // Act
    var result = teacherService.update(null);

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(teacherMapper, Mockito.times(0)).selectById(anyLong());
    Mockito.verify(teacherMapper, Mockito.times(0)).updateById(any(Teacher.class));
  }

  @Test
  void update_failed_by_no_course_found() {
    // Arrange
    Mockito.when(teacherMapper.selectById(anyLong())).thenReturn(null);

    // Act
    var result = teacherService.update(mockTeacher);

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(teacherMapper, Mockito.times(1)).selectById(anyLong());
    Mockito.verify(teacherMapper, Mockito.times(0)).updateById(any(Teacher.class));
  }

  @Test
  void update_failed_by_return_value() {
    // Arrange
    Mockito.when(teacherMapper.selectById(anyLong())).thenReturn(mockTeacher);
    Mockito.when(teacherMapper.updateById(any(Teacher.class))).thenReturn(0);

    // Act
    var result = teacherService.update(mockTeacher);

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(teacherMapper, Mockito.times(1)).selectById(anyLong());
    Mockito.verify(teacherMapper, Mockito.times(1)).updateById(any(Teacher.class));
  }

  @Test
  void delete_success(){
    // Arrange
    Mockito.when(teacherMapper.deleteById(anyLong())).thenReturn(1);

    // Act
    var result = teacherService.delete(mockTeacher.getId());

    // Assert
    Assertions.assertEquals(1, result);
    Mockito.verify(teacherMapper,Mockito.times(1)).deleteById(anyLong());
  }

  @Test
  void delete_failed_by_empty_param(){
    // Arrange

    // Act
    var result = teacherService.delete(null);

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(teacherMapper,Mockito.times(0)).deleteById(anyLong());
  }

  @Test
  void delete_failed_by_return_value(){
    // Arrange
    Mockito.when(teacherMapper.deleteById(anyLong())).thenReturn(0);

    // Act
    var result = teacherService.delete(mockTeacher.getId());

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(teacherMapper,Mockito.times(1)).deleteById(anyLong());
  }

  @Test
  void get_success(){
    // Arrange
    Mockito.when(teacherMapper.selectLinkById(anyLong())).thenReturn(mockTeacher);

    // Act
    var result = teacherService.get(anyLong());

    // Assert
    Assertions.assertEquals(mockTeacher, result);
    Mockito.verify(teacherMapper,Mockito.times(1)).selectLinkById(anyLong());
  }

  @Test
  void get_failed_by_empty_param(){
    // Arrange

    // Act
    var result = teacherService.get(null);

    // Assert
    Assertions.assertNull(result);
    Mockito.verify(teacherMapper,Mockito.times(0)).selectLinkById(anyLong());
  }

  @Test
  void get_failed_by_return_value(){
    // Arrange
    Mockito.when(teacherMapper.selectLinkById(anyLong())).thenReturn(null);

    // Act
    var result = teacherService.get(anyLong());

    // Assert
    Assertions.assertNull(result);
    Mockito.verify(teacherMapper,Mockito.times(1)).selectLinkById(anyLong());
  }

}
