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

import com.springboot.placement.dto.ApplicationResponseDTO;
import com.springboot.placement.entity.Application;
import com.springboot.placement.mapper.ApplicationMapper;
import com.springboot.placement.repository.ApplicationRepository;
import com.springboot.placement.repository.JobDriveRepository;
import com.springboot.placement.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceImplTest {

	@Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private JobDriveRepository jobDriveRepository;

    @Mock
    private ApplicationMapper applicationMapper;

    @InjectMocks
    private ApplicationServiceImpl applicationService;

    @Test
    void testGetApplicationById_Success() {
        Application application = new Application();
        ApplicationResponseDTO responseDTO = new ApplicationResponseDTO();
        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
        when(applicationMapper.toResponse(application)).thenReturn(responseDTO);
        ApplicationResponseDTO result = applicationService.getApplicationById(1L);
        assertNotNull(result);
        verify(applicationRepository).findById(1L);
        verify(applicationMapper).toResponse(application);
    }

    @Test
    void testDeleteApplication_Success() {
        when(applicationRepository.existsById(1L)).thenReturn(true);
        assertDoesNotThrow(() -> applicationService.deleteApplication(1L));
        verify(applicationRepository).existsById(1L);
        verify(applicationRepository).deleteById(1L);
    }
}


