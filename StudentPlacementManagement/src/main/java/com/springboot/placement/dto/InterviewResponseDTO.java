package com.springboot.placement.dto;

import java.time.LocalDateTime;

import com.springboot.placement.enums.InterviewMode;
import com.springboot.placement.enums.InterviewResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewResponseDTO {

	private Long interviewId;
    private LocalDateTime interviewDate;
    private InterviewMode mode;
    private InterviewResult result;
    private String feedback;

    private Long applicationId;
    private Long studentId;
    private String studentName;
    private Long jobDriveId;
    private String jobTitle;
}

