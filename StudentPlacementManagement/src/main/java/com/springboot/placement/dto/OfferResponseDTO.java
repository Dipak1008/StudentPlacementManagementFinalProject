package com.springboot.placement.dto;

import java.time.LocalDate;

import com.springboot.placement.enums.OfferStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferResponseDTO {

	private Long offerId;
    private LocalDate offerDate;
    private Double salary;
    private String requiredbranch;
    private LocalDate joiningDate;
    private OfferStatus status;

    private Long applicationId;
    private Long studentId;
    private String studentName;
    private Long jobDriveId;
    private String jobTitle;
    private Long companyId;
    private String companyName;
} 