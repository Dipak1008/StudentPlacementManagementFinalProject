package com.springboot.placement.dto;

import lombok.Data;

@Data
public class EligibilityResponseDTO {

	 private Long studentId;
	 private Long jobDriveId;
	 private boolean eligible;
	 private String message;
	

}


