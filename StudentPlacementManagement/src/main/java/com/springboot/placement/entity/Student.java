package com.springboot.placement.entity;

import java.util.ArrayList;
import java.util.List;

import com.springboot.placement.enums.Role;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Audit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentId;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private String collegeName;
	
	private String degree;
	
	private String branch;
	
	private Double cgpa;
	
	private Integer graduationYear;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@ManyToMany
	@JoinTable(
	        name = "student_job_drive",
	        joinColumns = @JoinColumn(name = "student_id"),
	        inverseJoinColumns = @JoinColumn(name = "job_drive_id")
	    )
	private List<JobDrive> jobDrives = new ArrayList<>();
	}

