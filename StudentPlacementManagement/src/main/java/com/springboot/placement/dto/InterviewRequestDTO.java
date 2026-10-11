package com.springboot.placement.dto;

import java.time.LocalDateTime;

import com.springboot.placement.enums.InterviewMode;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewRequestDTO {

	@NotNull(message = "Interview date is required")
	private LocalDateTime interviewDate;
	
	@NotNull(message = "Interview mode is required")
	private InterviewMode mode;
	
	@NotNull(message = "Application Id is required")
	private Long applicationId;
	
	private String feedback;
}
