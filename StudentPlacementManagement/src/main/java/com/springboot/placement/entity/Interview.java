package com.springboot.placement.entity;

import java.time.LocalDateTime;

import com.springboot.placement.enums.InterviewMode;
import com.springboot.placement.enums.InterviewResult;

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
@Table(name = "interviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Interview extends Audit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long interviewId;
	
	private LocalDateTime interviewDate;
	
	@Enumerated(EnumType.STRING)
	private InterviewMode mode;
	
	@Enumerated(EnumType.STRING)
	private InterviewResult result;
	
	private String feedback;
	
	@OneToOne
	@JoinColumn(name = "application_id")
	private Application application;
	
}
