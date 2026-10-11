package com.springboot.placement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.placement.dto.InterviewRequestDTO;
import com.springboot.placement.dto.InterviewResponseDTO;
import com.springboot.placement.enums.InterviewResult;

public interface InterviewService {

	 InterviewResponseDTO createInterview(InterviewRequestDTO dto);

	 InterviewResponseDTO getInterviewById(Long interviewId);

	 List<InterviewResponseDTO> getAllInterviews();

	 List<InterviewResponseDTO> getInterviewsByResult(InterviewResult result);

	 List<InterviewResponseDTO> getInterviewsByStudent(Long studentId);

	 InterviewResponseDTO updateInterviewResult(Long interviewId, InterviewResult result);

	 void deleteInterview(Long interviewId);
	
	 Page<InterviewResponseDTO> getInterviewsWithPagination(int page, int size, String sortBy, String direction); 

}
