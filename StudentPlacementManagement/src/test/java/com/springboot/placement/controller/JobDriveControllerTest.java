package com.springboot.placement.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.springboot.placement.dto.JobDriveResponseDTO;
import com.springboot.placement.service.JobDriveService;

@WebMvcTest(JobDriveController.class)
public class JobDriveControllerTest {

	    @Autowired
	    private MockMvc mockMvc;

	    @MockitoBean
	    private JobDriveService jobDriveService;

	    @MockitoBean
	    private CacheManager cacheManager;

	    @Test
	    void testGetJobDriveById_Success() throws Exception {
	        JobDriveResponseDTO response = new JobDriveResponseDTO();
	        when(jobDriveService.getJobDriveById(1L)).thenReturn(response);
	        mockMvc.perform(get("/api/job-drives/1")).andExpect(status().isOk());
	        verify(jobDriveService).getJobDriveById(1L);
	    }

	    @Test
	    void testDeleteJobDrive_Success() throws Exception {
	        doNothing().when(jobDriveService).deleteJobDrive(1L);
	        mockMvc.perform(delete("/api/job-drives/1")).andExpect(status().isNoContent());
	        verify(jobDriveService).deleteJobDrive(1L);
	    }
	}


