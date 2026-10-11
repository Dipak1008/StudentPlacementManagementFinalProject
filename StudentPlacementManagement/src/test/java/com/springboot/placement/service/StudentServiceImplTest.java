package com.springboot.placement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springboot.placement.dto.StudentRequestDTO;
import com.springboot.placement.dto.StudentResponseDTO;
import com.springboot.placement.entity.Student;
import com.springboot.placement.exception.ResourceNotFoundException;
import com.springboot.placement.mapper.StudentMapper;
import com.springboot.placement.repository.JobDriveRepository;
import com.springboot.placement.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

	@Mock
    private StudentRepository studentRepository;

    @Mock
    private JobDriveRepository jobDriveRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void testGetStudentById_Success() {

        Student student = new Student();
        student.setStudentId(1L);
        student.setName("Nilesh");
        student.setEmail("nilesh@gmail.com");

        StudentResponseDTO response = new StudentResponseDTO();
        response.setStudentId(1L);
        response.setName("Martha");
        response.setEmail("martha@gmail.com");

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        when(studentMapper.toResponse(student))
                .thenReturn(response);

        StudentResponseDTO result = studentService.getStudentById(1L);

        assertEquals("Martha", result.getName());
        assertEquals("martha@gmail.com", result.getEmail());
    }

    @Test
    void testGetStudentById_NotFound() {

        when(studentRepository.findById(2L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> studentService.getStudentById(2L));
    }

    @Test
    void testCreateStudent() {

        StudentRequestDTO request = new StudentRequestDTO();
        request.setName("John");
        request.setEmail("john@gmail.com");

        Student student = new Student();
        student.setName("John");
        student.setEmail("john@gmail.com");

        Student savedStudent = new Student();
        savedStudent.setStudentId(3L);
        savedStudent.setName("John");
        savedStudent.setEmail("john@gmail.com");

        StudentResponseDTO response = new StudentResponseDTO();
        response.setStudentId(3L);
        response.setName("John");
        response.setEmail("john@gmail.com");

        when(studentMapper.toEntity(request)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(savedStudent);
        when(studentMapper.toResponse(savedStudent)).thenReturn(response);

        StudentResponseDTO result = studentService.createStudent(request);

        assertEquals(3L, result.getStudentId());
        assertEquals("John", result.getName());

        verify(studentRepository).save(student);
    }
}
