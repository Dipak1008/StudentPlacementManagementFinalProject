package com.springboot.placement.mapper;

import org.springframework.stereotype.Component;

import com.springboot.placement.dto.InterviewResponseDTO;
import com.springboot.placement.entity.Interview;

@Component
public class InterviewMapper {

	public InterviewResponseDTO toResponse(Interview interview) {

	        InterviewResponseDTO dto = new InterviewResponseDTO();
	        dto.setInterviewId(interview.getInterviewId());
	        dto.setInterviewDate(interview.getInterviewDate());
	        dto.setMode(interview.getMode());
	        dto.setResult(interview.getResult());
	        dto.setFeedback(interview.getFeedback());

	        if (interview.getApplication() != null) {
	            dto.setApplicationId(interview.getApplication().getApplicationId());
	           
	            if (interview.getApplication().getStudent() != null) {
	                dto.setStudentId(interview.getApplication().getStudent().getStudentId());

	                dto.setStudentName(interview.getApplication().getStudent().getName());
	            }

	            if (interview.getApplication().getJobDrive() != null) {
	                dto.setJobDriveId(interview.getApplication().getJobDrive().getJobDriveId());

	                dto.setJobTitle(interview.getApplication().getJobDrive().getJobTitle());
	            }
	        }

	        return dto;
	    }
	}