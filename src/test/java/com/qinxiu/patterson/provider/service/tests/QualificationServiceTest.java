package com.qinxiu.patterson.provider.service.tests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import com.qinxiu.patterson.provider.domain.Course;
import com.qinxiu.patterson.provider.domain.Qualification;
import com.qinxiu.patterson.provider.mapper.IQualificationMapper;
import com.qinxiu.patterson.provider.service.QualificationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class QualificationServiceTest {

  @Mock
  IQualificationMapper qualificationMapper;

  @Mock
  Qualification mockQualification;

  @InjectMocks
  QualificationService qualificationService;

  @Test
  void insert_success(){
    // Arrange
    Mockito.when(qualificationMapper.insert(any(Qualification.class))).thenReturn(1);

    // Act
    var result = qualificationService.insert(mockQualification);

    // Assert
    Assertions.assertEquals(1, result);
    Mockito.verify(qualificationMapper,Mockito.times(1)).insert(any(Qualification.class));
  }

  @Test
  void insert_failed_by_empty_param(){
    // Arrange

    // Act
    var result = qualificationService.insert(null);

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(qualificationMapper,Mockito.times(0)).insert(any(Qualification.class));
  }

  @Test
  void insert_failed_by_return_value(){
    // Arrange
    Mockito.when(qualificationMapper.insert(any(Qualification.class))).thenReturn(0);

    // Act
    var result = qualificationService.insert(mockQualification);

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(qualificationMapper,Mockito.times(1)).insert(any(Qualification.class));
  }

  @Test
  void update_success() {
    // Arrange
    Mockito.when(qualificationMapper.selectById(anyLong())).thenReturn(mockQualification);
    Mockito.when(qualificationMapper.updateById(any(Qualification.class))).thenReturn(1);

    // Act
    var result = qualificationService.update(mockQualification);

    // Assert
    Assertions.assertEquals(1, result);
    Mockito.verify(qualificationMapper, Mockito.times(1)).selectById(anyLong());
    Mockito.verify(qualificationMapper, Mockito.times(1)).updateById(any(Qualification.class));
  }

  @Test
  void update_failed_by_empty_param() {
    // Arrange

    // Act
    var result = qualificationService.update(null);

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(qualificationMapper, Mockito.times(0)).selectById(anyLong());
    Mockito.verify(qualificationMapper, Mockito.times(0)).updateById(any(Qualification.class));
  }

  @Test
  void update_failed_by_no_course_found() {
    // Arrange
    Mockito.when(qualificationMapper.selectById(anyLong())).thenReturn(null);

    // Act
    var result = qualificationService.update(mockQualification);

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(qualificationMapper, Mockito.times(1)).selectById(anyLong());
    Mockito.verify(qualificationMapper, Mockito.times(0)).updateById(any(Qualification.class));
  }

  @Test
  void update_failed_by_return_value() {
    // Arrange
    Mockito.when(qualificationMapper.selectById(anyLong())).thenReturn(mockQualification);
    Mockito.when(qualificationMapper.updateById(any(Qualification.class))).thenReturn(0);

    // Act
    var result = qualificationService.update(mockQualification);

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(qualificationMapper, Mockito.times(1)).selectById(anyLong());
    Mockito.verify(qualificationMapper, Mockito.times(1)).updateById(any(Qualification.class));
  }

  @Test
  void delete_success(){
    // Arrange
    Mockito.when(qualificationMapper.deleteById(anyLong())).thenReturn(1);

    // Act
    var result = qualificationService.delete(mockQualification.getId());

    // Assert
    Assertions.assertEquals(1, result);
    Mockito.verify(qualificationMapper,Mockito.times(1)).deleteById(anyLong());
  }

  @Test
  void delete_failed_by_empty_param(){
    // Arrange

    // Act
    var result = qualificationService.delete(null);

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(qualificationMapper,Mockito.times(0)).deleteById(anyLong());
  }

  @Test
  void delete_failed_by_return_value(){
    // Arrange
    Mockito.when(qualificationMapper.deleteById(anyLong())).thenReturn(0);

    // Act
    var result = qualificationService.delete(mockQualification.getId());

    // Assert
    Assertions.assertEquals(0, result);
    Mockito.verify(qualificationMapper,Mockito.times(1)).deleteById(anyLong());
  }

  @Test
  void get_success(){
    // Arrange
    Mockito.when(qualificationMapper.selectById(anyLong())).thenReturn(mockQualification);

    // Act
    var result = qualificationService.get(anyLong());

    // Assert
    Assertions.assertEquals(mockQualification, result);
    Mockito.verify(qualificationMapper,Mockito.times(1)).selectById(anyLong());
  }

  @Test
  void get_failed_by_empty_param(){
    // Arrange

    // Act
    var result = qualificationService.get(null);

    // Assert
    Assertions.assertNull(result);
    Mockito.verify(qualificationMapper,Mockito.times(0)).selectById(anyLong());
  }

  @Test
  void get_failed_by_return_value(){
    // Arrange
    Mockito.when(qualificationMapper.selectById(anyLong())).thenReturn(null);

    // Act
    var result = qualificationService.get(anyLong());

    // Assert
    Assertions.assertNull(result);
    Mockito.verify(qualificationMapper,Mockito.times(1)).selectById(anyLong());
  }
}
