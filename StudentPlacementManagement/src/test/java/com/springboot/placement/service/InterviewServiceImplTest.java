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

import com.springboot.placement.dto.InterviewResponseDTO;
import com.springboot.placement.entity.Interview;
import com.springboot.placement.mapper.InterviewMapper;
import com.springboot.placement.repository.ApplicationRepository;
import com.springboot.placement.repository.InterviewRepository;

@ExtendWith(MockitoExtension.class)
public class InterviewServiceImplTest {

	  @Mock
	    private InterviewRepository interviewRepository;

	    @Mock
	    private ApplicationRepository applicationRepository;

	    @Mock
	    private InterviewMapper interviewMapper;

	    @InjectMocks
	    private InterviewServiceImpl interviewService;

	    @Test
	    void testGetInterviewById_Success() {
	        Interview interview = new Interview();
	        InterviewResponseDTO responseDTO = new InterviewResponseDTO();
	        when(interviewRepository.findById(1L)).thenReturn(Optional.of(interview));
	        when(interviewMapper.toResponse(interview)).thenReturn(responseDTO);
	        InterviewResponseDTO result = interviewService.getInterviewById(1L);
	        assertNotNull(result);
	        verify(interviewRepository).findById(1L);
	        verify(interviewMapper).toResponse(interview);
	    }

	    @Test
	    void testDeleteInterview_Success() {
	        when(interviewRepository.existsById(1L)).thenReturn(true);
	        assertDoesNotThrow(() -> interviewService.deleteInterview(1L));
	        verify(interviewRepository).existsById(1L);
	        verify(interviewRepository).deleteById(1L);
	    }
	

}

