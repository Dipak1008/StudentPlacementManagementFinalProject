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

import com.springboot.placement.dto.ApplicationResponseDTO;
import com.springboot.placement.service.ApplicationService;

@WebMvcTest(ApplicationController.class)
public class ApplicationControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ApplicationService applicationService;

    @MockitoBean
    private CacheManager cacheManager;

    @Test
    void testGetAllApplications_Success() throws Exception {
        ApplicationResponseDTO responseDTO = new ApplicationResponseDTO();
        when(applicationService.getAllApplications()).thenReturn(List.of(responseDTO));
        mockMvc.perform(get("/api/applications")).andExpect(status().isOk());
        verify(applicationService).getAllApplications();
    }

    @Test
    void testDeleteApplication_Success() throws Exception {
        doNothing().when(applicationService).deleteApplication(1L);
        mockMvc.perform(delete("/api/applications/1")).andExpect(status().isNoContent());
        verify(applicationService).deleteApplication(1L);
    }
}


