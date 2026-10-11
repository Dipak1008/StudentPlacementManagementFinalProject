package com.springboot.placement.dto;

import java.time.LocalDate;

import com.springboot.placement.enums.OfferStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequestDTO {

	@NotNull(message = "Offer date is required")
	private LocalDate offerDate;

	@NotNull(message = "Salary is required")
	@PositiveOrZero(message = "Salary cannot be negative")
	private Double salary;
	
	@NotNull(message = "Required branch is required")
	private String requiredbranch;
	
	@NotNull(message = "Joining date is required")
	private LocalDate joiningDate;
	
	private OfferStatus status;

	@NotNull(message = "Appication ID is required")
	private Long applicationId;
	
	
}
