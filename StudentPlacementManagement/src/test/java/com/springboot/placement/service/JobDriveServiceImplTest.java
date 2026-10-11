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

import com.springboot.placement.dto.JobDriveResponseDTO;
import com.springboot.placement.entity.JobDrive;
import com.springboot.placement.mapper.JobDriveMapper;
import com.springboot.placement.repository.CompanyRepository;
import com.springboot.placement.repository.JobDriveRepository;

@ExtendWith(MockitoExtension.class)
public class JobDriveServiceImplTest {

	   @Mock
	    private JobDriveRepository jobDriveRepository;

	    @Mock
	    private CompanyRepository companyRepository;

	    @Mock
	    private JobDriveMapper jobDriveMapper;

	    @InjectMocks
	    private JobDriveServiceImpl jobDriveService;

	    @Test
	    void testGetJobDriveById_Success() {
	        JobDrive jobDrive = new JobDrive();
	        when(jobDriveRepository.findById(1L)).thenReturn(Optional.of(jobDrive));
	        JobDriveResponseDTO response = new JobDriveResponseDTO();
	        when(jobDriveMapper.toResponse(jobDrive)).thenReturn(response);

	        JobDriveResponseDTO result = jobDriveService.getJobDriveById(1L);
	        assertNotNull(result);
	        verify(jobDriveRepository).findById(1L);
	        verify(jobDriveMapper).toResponse(jobDrive);
	    }

	    @Test
	    void testDeleteJobDrive_Success() {
	        when(jobDriveRepository.existsById(1L)).thenReturn(true);
	        assertDoesNotThrow(() -> jobDriveService.deleteJobDrive(1L));
	        verify(jobDriveRepository).deleteById(1L);
	    }
	}

