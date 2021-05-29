package com.qinxiu.patterson.provider.service.tests;

import com.qinxiu.patterson.provider.domain.Course;
import com.qinxiu.patterson.provider.mapper.ICourseMapper;
import com.qinxiu.patterson.provider.service.CourseService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
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
    Mockito.when(courseMapper.selectLinkById(anyLong())).thenReturn(mockCourse);
    Mockito.when(courseMapper.updateById(any(Course.class))).thenReturn(1);

    // Act
    var result = courseService.update(mockCourse);

    // Assert
    Assertions.assertEquals(1, result);
    Mockito.verify(courseMapper, Mockito.times(1)).selectLinkById(anyLong());
    Mockito.verify(courseMapper, Mockito.times(1)).updateById(any(Course.class));
  }

  @Test
  void update_failed_by_empty_param() {
    // Arrange

    // Act
    var result = courseService.update(null);

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(courseMapper, Mockito.times(0)).selectLinkById(anyLong());
    Mockito.verify(courseMapper, Mockito.times(0)).updateById(any(Course.class));
  }

  @Test
  void update_failed_by_no_course_found() {
    // Arrange
    Mockito.when(courseMapper.selectLinkById(anyLong())).thenReturn(null);

    // Act
    var result = courseService.update(mockCourse);

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(courseMapper, Mockito.times(1)).selectLinkById(anyLong());
    Mockito.verify(courseMapper, Mockito.times(0)).updateById(any(Course.class));
  }

  @Test
  void update_failed_by_return_value() {
    // Arrange
    Mockito.when(courseMapper.selectLinkById(anyLong())).thenReturn(mockCourse);
    Mockito.when(courseMapper.updateById(any(Course.class))).thenReturn(0);

    // Act
    var result = courseService.update(mockCourse);

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(courseMapper, Mockito.times(1)).selectLinkById(anyLong());
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
    Mockito.when(courseMapper.selectLinkById(anyLong())).thenReturn(mockCourse);

    // Act
    var result = courseService.get(anyLong());

    // Assert
    Assertions.assertEquals(mockCourse, result);
    Mockito.verify(courseMapper,Mockito.times(1)).selectLinkById(anyLong());
  }

  @Test
  void get_failed_by_empty_param(){
    // Arrange

    // Act
    var result = courseService.get(null);

    // Assert
    Assertions.assertNull(result);
    Mockito.verify(courseMapper,Mockito.times(0)).selectLinkById(anyLong());
  }

  @Test
  void get_failed_by_return_value(){
    // Arrange
    Mockito.when(courseMapper.selectLinkById(anyLong())).thenReturn(null);

    // Act
    var result = courseService.get(anyLong());

    // Assert
    Assertions.assertNull(result);
    Mockito.verify(courseMapper,Mockito.times(1)).selectLinkById(anyLong());
  }

  @Test
  void getAll_success(){
    // Arrange
    var courseList = new ArrayList<Course>() {{add(mockCourse);}};
    Mockito.when(courseMapper.selectAll()).thenReturn(courseList);

    // Act
    var result = courseService.getAll();

    // Assert
    Assertions.assertEquals(courseList, result);
    Mockito.verify(courseMapper,Mockito.times(1)).selectAll();
  }

  @Test
  void getAll_failed_by_return_value(){
    // Arrange
    Mockito.when(courseMapper.selectAll()).thenReturn(null);

    // Act
    var result = courseService.getAll();

    // Assert
    Assertions.assertNull(result);
    Mockito.verify(courseMapper,Mockito.times(1)).selectAll();
  }

  @Test
  void getCourseByTeacherId_success(){
    // Arrange
    var courseList = new ArrayList<Course>() {{add(mockCourse);}};
    Mockito.when(courseMapper.selectByTeacherId(anyLong())).thenReturn(courseList);

    // Act
    var result = courseService.getCourseByTeacherId(anyLong());

    // Assert
    Assertions.assertEquals(courseList, result);
    Mockito.verify(courseMapper,Mockito.times(1)).selectByTeacherId(anyLong());
  }

  @Test
  void getCourseByTeacherId_failed_by_empty_param(){
    // Arrange

    // Act
    var result = courseService.getCourseByTeacherId(null);

    // Assert
    Assertions.assertNull(result);
    Mockito.verify(courseMapper,Mockito.times(0)).selectByTeacherId(anyLong());
  }

  @Test
  void getCourseByTeacherId_failed_by_return_value(){
    // Arrange
    Mockito.when(courseMapper.selectByTeacherId(anyLong())).thenReturn(null);

    // Act
    var result = courseService.getCourseByTeacherId(anyLong());

    // Assert
    Assertions.assertNull(result);
    Mockito.verify(courseMapper,Mockito.times(1)).selectByTeacherId(anyLong());
  }


}
