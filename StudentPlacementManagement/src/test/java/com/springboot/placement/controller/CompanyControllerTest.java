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

import com.springboot.placement.dto.CompanyResponseDTO;
import com.springboot.placement.service.CompanyService;

@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

	    @Autowired
	    private MockMvc mockMvc;

	    @MockitoBean
	    private CompanyService companyService;

	    @MockitoBean
	    private CacheManager cacheManager;

	    @Test
	    void testGetAllCompanies_Success() throws Exception {
	        CompanyResponseDTO responseDTO = new CompanyResponseDTO();
	        when(companyService.getAllCompanies()).thenReturn(List.of(responseDTO));
	        mockMvc.perform(get("/api/companies")).andExpect(status().isOk());
	        verify(companyService).getAllCompanies();
	    }

	    @Test
	    void testDeleteCompany_Success() throws Exception {
	        doNothing().when(companyService).deleteCompany(1L);
	        mockMvc.perform(delete("/api/companies/1")).andExpect(status().isNoContent());
	        verify(companyService).deleteCompany(1L);
	    }
	
}
