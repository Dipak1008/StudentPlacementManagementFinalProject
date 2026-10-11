package com.springboot.placement.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.placement.constants.PlacementConstants;
import com.springboot.placement.dto.InterviewRequestDTO;
import com.springboot.placement.dto.InterviewResponseDTO;
import com.springboot.placement.entity.Application;
import com.springboot.placement.entity.Interview;
import com.springboot.placement.enums.InterviewResult;
import com.springboot.placement.exception.ResourceNotFoundException;
import com.springboot.placement.mapper.InterviewMapper;
import com.springboot.placement.repository.ApplicationRepository;
import com.springboot.placement.repository.InterviewRepository;

import jakarta.transaction.Transactional;

@Service
public class InterviewServiceImpl implements InterviewService {
	
    private static final Logger log = LoggerFactory.getLogger(InterviewServiceImpl.class);

    @Autowired
    InterviewRepository interviewRepository;
    
    @Autowired
    ApplicationRepository applicationRepository;
    
    @Autowired
    InterviewMapper interviewMapper;
   
	@Override
	@Transactional
	public InterviewResponseDTO createInterview(InterviewRequestDTO dto) {
		log.info("Creating interview for application ID: {}", dto.getApplicationId());

        Application application = applicationRepository.findById(dto.getApplicationId())
             .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.APPLICATION_NOT_FOUND + dto.getApplicationId()));

        Interview interview = new Interview();
        interview.setInterviewDate(dto.getInterviewDate());
        interview.setMode(dto.getMode());
        interview.setResult(InterviewResult.PENDING);
        interview.setFeedback(dto.getFeedback());
        interview.setApplication(application);

        Interview savedInterview = interviewRepository.save(interview);

        log.info("Interview created successfully with ID: {}", savedInterview.getInterviewId());

        return interviewMapper.toResponse(savedInterview);
    
	}


	@Override
	public InterviewResponseDTO getInterviewById(Long interviewId) {
		 log.info("Fetching interview with ID: {}", interviewId);

	     Interview interview = interviewRepository.findById(interviewId)
	              .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.INTERVIEW_NOT_FOUND + interviewId));
	     return interviewMapper.toResponse(interview);
	   
	}


	@Override
	public List<InterviewResponseDTO> getAllInterviews() {
		log.info("Fetching all interviews");
	     return interviewRepository.findAll().stream().map(interviewMapper::toResponse).toList();
	   
	}

	
	@Override
	public List<InterviewResponseDTO> getInterviewsByResult(InterviewResult result) {
		 log.info("Fetching interviews by result: {}", result);
         return interviewRepository.findByResult(result).stream().map(interviewMapper::toResponse).toList();
	    
	}


	@Override
	public List<InterviewResponseDTO> getInterviewsByStudent(Long studentId) {
		 log.info("Fetching interviews for student ID: {}", studentId);
         return interviewRepository.findByApplicationStudentStudentId(studentId)
	                .stream().map(interviewMapper :: toResponse).toList();
	    
	}


	@Override
	@Transactional
	public InterviewResponseDTO updateInterviewResult(Long interviewId, InterviewResult result) {
		 log.info("Updating interview ID: {} with result: {}", interviewId, result);
	       
		 Interview interview = interviewRepository.findById(interviewId)
	                .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.INTERVIEW_NOT_FOUND + interviewId));

	     interview.setResult(result);
	     Interview updatedInterview = interviewRepository.save(interview);

	     log.info("Interview result updated successfully");

	      return interviewMapper.toResponse(updatedInterview);
	    
	}


	@Override
	@Transactional
	public void deleteInterview(Long interviewId) {
        log.info("Deleting interview with ID: {}", interviewId);

        if (!interviewRepository.existsById(interviewId)) {
            throw new ResourceNotFoundException(PlacementConstants.INTERVIEW_NOT_FOUND + interviewId);
        }

        interviewRepository.deleteById(interviewId);
        log.info("Interview deleted successfully with ID: {}", interviewId);
    
	}


	@Override
	public Page<InterviewResponseDTO> getInterviewsWithPagination(int page, int size, String sortBy, String direction) {
		 log.info("Fetching interviews with pagination");
		 Sort sort;
		    if (direction.equalsIgnoreCase("desc")) {
		        sort = Sort.by(sortBy).descending();
		    } else {
		        sort = Sort.by(sortBy).ascending();
		    
		    }

		    PageRequest pageRequest = PageRequest.of(page, size, sort);
		    Page<Interview> interviews = interviewRepository.findAll(pageRequest);
		    return interviews.map(interviewMapper::toResponse);
		}
}
