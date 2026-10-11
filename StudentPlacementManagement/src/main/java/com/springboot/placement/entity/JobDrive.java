package com.springboot.placement.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.springboot.placement.enums.JobDriveStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "job_drives")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDrive extends Audit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobDriveId;
    
	private String jobTitle;
	
	private String description;
	
	private Double minimumCgpa;
	
	private String requiredbranch;
	
	private String location;
		
	private LocalDate driveDate;
	
	private LocalDate applicationDeadline;
	
	@Enumerated(EnumType.STRING)
	private JobDriveStatus status;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	@ManyToMany(mappedBy = "jobDrives")
    private List<Student> students = new ArrayList<>();
	
}
