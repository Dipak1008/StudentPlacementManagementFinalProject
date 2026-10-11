package com.springboot.placement.entity;

import java.time.LocalDate;

import com.springboot.placement.enums.OfferStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "offers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer  extends Audit{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long offerId;
	
	private LocalDate offerDate;
   
	private String requiredBranch;
	
	private Double salary;

	private LocalDate joiningDate;
	
	@Enumerated(EnumType.STRING)
	private OfferStatus status;
	
	@OneToOne
	@JoinColumn(name = "application_id")
	private Application application;
	
	
}
