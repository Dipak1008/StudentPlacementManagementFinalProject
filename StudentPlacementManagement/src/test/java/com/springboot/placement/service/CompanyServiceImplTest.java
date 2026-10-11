package com.springboot.placement.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springboot.placement.dto.CompanyResponseDTO;
import com.springboot.placement.entity.Company;
import com.springboot.placement.mapper.CompanyMapper;
import com.springboot.placement.repository.CompanyRepository;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceImplTest {

	    @Mock
	    private CompanyRepository companyRepository;

	    @Mock
	    private CompanyMapper companyMapper;

	    @InjectMocks
	    private CompanyServiceImpl companyService;

	    @Test
	    void testGetCompanyById_Success() {

	        Company company = new Company();
	        CompanyResponseDTO responseDTO = new CompanyResponseDTO();
	        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
	        when(companyMapper.toResponse(company)).thenReturn(responseDTO);
	        CompanyResponseDTO result = companyService.getCompanyById(1L);
	        assertNotNull(result);
	        verify(companyRepository).findById(1L);
	        verify(companyMapper).toResponse(company);
	    }

	    @Test
	    void testDeleteCompany_Success() {
	        when(companyRepository.existsById(1L)).thenReturn(true);
	        assertDoesNotThrow(() -> companyService.deleteCompany(1L));
	        verify(companyRepository).existsById(1L);
	        verify(companyRepository).deleteById(1L);
	    }
	}

