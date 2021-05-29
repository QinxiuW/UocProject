package com.qinxiu.patterson.provider.service.tests;

import com.qinxiu.patterson.provider.domain.Course;
import com.qinxiu.patterson.provider.mapper.ICourseMapper;
import com.qinxiu.patterson.provider.service.CourseService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

  @Mock
  ICourseMapper courseMapper;

  @Mock
  Course mockCourse;

  @InjectMocks
  CourseService courseService;

  @Test
  void insert_success() {
    // Arrange
    Mockito.when(courseMapper.insert(any(Course.class))).thenReturn(1);

    // Act
    var result = courseService.insert(mockCourse);

    // Assert
    Assertions.assertEquals(1, result);
    Mockito.verify(courseMapper, Mockito.times(1)).insert(any(Course.class));
  }

  @Test
  void insert_failed_by_empty_param() {
    // Arrange

    // Act
    var result = courseService.insert(null);

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(courseMapper, Mockito.times(0)).insert(any(Course.class));
  }

  @Test
  void insert_failed_by_return_value() {
    // Arrange
    Mockito.when(courseMapper.insert(any(Course.class))).thenReturn(0);

    // Act
    var result = courseService.insert(mockCourse);

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(courseMapper, Mockito.times(1)).insert(any(Course.class));
  }

  @Test
  void update_success() {
    // Arrange
    Mockito.when(courseMapper.selectById(anyLong())).thenReturn(mockCourse);
    Mockito.when(courseMapper.updateById(any(Course.class))).thenReturn(1);

    // Act
    var result = courseService.update(mockCourse);

    // Assert
    Assertions.assertEquals(1, result);
    Mockito.verify(courseMapper, Mockito.times(1)).selectById(anyLong());
    Mockito.verify(courseMapper, Mockito.times(1)).updateById(any(Course.class));
  }

  @Test
  void update_failed_by_empty_param() {
    // Arrange

    // Act
    var result = courseService.update(null);

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(courseMapper, Mockito.times(0)).selectById(anyLong());
    Mockito.verify(courseMapper, Mockito.times(0)).updateById(any(Course.class));
  }

  @Test
  void update_failed_by_no_course_found() {
    // Arrange
    Mockito.when(courseMapper.selectById(anyLong())).thenReturn(null);

    // Act
    var result = courseService.update(mockCourse);

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(courseMapper, Mockito.times(1)).selectById(anyLong());
    Mockito.verify(courseMapper, Mockito.times(0)).updateById(any(Course.class));
  }

  @Test
  void update_failed_by_return_value() {
    // Arrange
    Mockito.when(courseMapper.selectById(anyLong())).thenReturn(mockCourse);
    Mockito.when(courseMapper.updateById(any(Course.class))).thenReturn(0);

    // Act
    var result = courseService.update(mockCourse);

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(courseMapper, Mockito.times(1)).selectById(anyLong());
    Mockito.verify(courseMapper, Mockito.times(1)).updateById(any(Course.class));
  }

  @Test
  void delete_success(){
    // Arrange
    Mockito.when(courseMapper.deleteById(anyLong())).thenReturn(1);

    // Act
    var result = courseService.delete(mockCourse.getId());

    // Assert
    Assertions.assertEquals(1, result);
    Mockito.verify(courseMapper,Mockito.times(1)).deleteById(anyLong());
  }

  @Test
  void delete_failed_by_empty_param(){
    // Arrange

    // Act
    var result = courseService.delete(null);

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(courseMapper,Mockito.times(0)).deleteById(anyLong());
  }

  @Test
  void delete_failed_by_return_value(){
    // Arrange
    Mockito.when(courseMapper.deleteById(anyLong())).thenReturn(0);

    // Act
    var result = courseService.delete(mockCourse.getId());

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(courseMapper,Mockito.times(1)).deleteById(anyLong());
  }

  @Test
  void get_success(){
    // Arrange
    Mockito.when(courseMapper.selectById(anyLong())).thenReturn(mockCourse);

    // Act
    var result = courseService.get(anyLong());

    // Assert
    Assertions.assertEquals(mockCourse, result);
    Mockito.verify(courseMapper,Mockito.times(1)).selectById(anyLong());
  }

  @Test
  void get_failed_by_empty_param(){
    // Arrange

    // Act
    var result = courseService.get(null);

    // Assert
    Assertions.assertNull(result);
    Mockito.verify(courseMapper,Mockito.times(0)).selectById(anyLong());
  }

  @Test
  void get_failed_by_return_value(){
    // Arrange
    Mockito.when(courseMapper.selectById(anyLong())).thenReturn(null);

    // Act
    var result = courseService.get(anyLong());

    // Assert
    Assertions.assertNull(result);
    Mockito.verify(courseMapper,Mockito.times(1)).selectById(anyLong());
  }

  @Test
  void getByName_success(){
    // Arrange
    Mockito.when(courseMapper.selectOne(any())).thenReturn(mockCourse);

    // Act
    // here we're not using anyString() because the empty string value is included.
    var result = courseService.getByName("name");

    // Assert
    Assertions.assertEquals(mockCourse, result);
    Mockito.verify(courseMapper,Mockito.times(1)).selectOne(any());
  }

  @Test
  void getByName_failed_by_empty_param(){
    // Arrange

    // Act
    var result = courseService.getByName(null);

    // Assert
    Assertions.assertNull(result);
    Mockito.verify(courseMapper,Mockito.times(0)).selectOne(any());
  }

  @Test
  void getByName_failed_by_return_value(){
    // Arrange
    Mockito.when(courseMapper.selectOne(any())).thenReturn(null);

    // Act
    // here we're not using anyString() because the empty string value is included.
    var result = courseService.getByName("name");

    // Assert
    Assertions.assertNull(result);
    Mockito.verify(courseMapper,Mockito.times(1)).selectOne(any());
  }
}
