package com.springboot.placement.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.springboot.placement.dto.StudentResponseDTO;
import com.springboot.placement.service.StudentService;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @MockitoBean
    private CacheManager cacheManager;
    
    @Test
    void testGetStudentById_Success() throws Exception {

        StudentResponseDTO response = new StudentResponseDTO();
        response.setStudentId(1L);
        response.setName("Martha");
        response.setEmail("martha@gmail.com");

        when(studentService.getStudentById(1L))
                .thenReturn(response);

        mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId").value(1))
                .andExpect(jsonPath("$.name").value("Martha"))
                .andExpect(jsonPath("$.email").value("martha@gmail.com"));
    }

    @Test
    void testGetAllStudents_Success() throws Exception {

        StudentResponseDTO response = new StudentResponseDTO();
        response.setStudentId(1L);
        response.setName("Martha");
        response.setEmail("martha@gmail.com");

        when(studentService.getAllStudents())
                .thenReturn(List.of(response));

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Martha"));
    }

}
