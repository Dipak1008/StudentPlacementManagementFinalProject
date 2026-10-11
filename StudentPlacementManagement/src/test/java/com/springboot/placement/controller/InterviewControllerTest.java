package com.springboot.placement.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.springboot.placement.dto.InterviewResponseDTO;
import com.springboot.placement.service.InterviewService;

@WebMvcTest(InterviewController.class)
public class InterviewControllerTest {

	   @Autowired
	    private MockMvc mockMvc;

	    @MockitoBean
	    private InterviewService interviewService;

	    @MockitoBean
	    private CacheManager cacheManager;

	    @Test
	    void testGetAllInterviews_Success() throws Exception {
	        InterviewResponseDTO responseDTO = new InterviewResponseDTO();
	        when(interviewService.getAllInterviews()).thenReturn(List.of(responseDTO));
	        mockMvc.perform(get("/api/interviews")).andExpect(status().isOk());
	        verify(interviewService).getAllInterviews();
	    }

	    @Test
	    void testDeleteInterview_Success() throws Exception {
	        doNothing().when(interviewService).deleteInterview(1L);
	        mockMvc.perform(delete("/api/interviews/1")).andExpect(status().isNoContent());
	        verify(interviewService).deleteInterview(1L);
	    }
	}