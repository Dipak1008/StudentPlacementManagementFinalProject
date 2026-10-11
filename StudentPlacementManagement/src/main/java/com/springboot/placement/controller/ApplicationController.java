package com.springboot.placement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.placement.dto.ApplicationRequestDTO;
import com.springboot.placement.dto.ApplicationResponseDTO;
import com.springboot.placement.enums.ApplicationStatus;
import com.springboot.placement.service.ApplicationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/applications")
@Tag(name = "Application Management", description = "APIs for managing job applications")
@Validated
public class ApplicationController {

	@Autowired
	ApplicationService applicationService;
	
	
	@Operation(
		      summary = "Apply for a job drive",
		    description = "Creates a new job application for a student"
		)
	@PostMapping
    public ResponseEntity<ApplicationResponseDTO> applyForJob(@Valid @RequestBody ApplicationRequestDTO dto) {
        ApplicationResponseDTO response = applicationService.applyForJob(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	@Operation(
		    summary = "Get all applications",
		    description = "Retrieves all job applications"
		)
    @GetMapping
    public ResponseEntity<List<ApplicationResponseDTO>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    
    }

	@Operation(
		    summary = "Get application by ID",
		    description = "Retrieves application details using the application ID"
		)
    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationResponseDTO> getApplicationById(@PathVariable Long applicationId) {
        return ResponseEntity.ok(applicationService.getApplicationById(applicationId));
    
    }

	
	
	@Operation(
		    summary = "Get applications by student",
		    description = "Retrieves all job applications submitted by a specific student"
		)
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ApplicationResponseDTO>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(applicationService.getApplicationsByStudent(studentId));
    
    }

	
	
	@Operation(
		    summary = "Get applications by job drive",
		    description = "Retrieves all applications submitted for a specific job drive"
		)
    @GetMapping("/job-drive/{jobDriveId}")
    public ResponseEntity<List<ApplicationResponseDTO>> getByJobDrive(@PathVariable Long jobDriveId) {
        return ResponseEntity.ok(applicationService.getApplicationsByJobDrive(jobDriveId));
    
    }

	
	
	@Operation(
		    summary = "Get applications by status",
		    description = "Retrieves applications based on their current status"
     )
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ApplicationResponseDTO>> getByStatus(@PathVariable ApplicationStatus status) {
        return ResponseEntity.ok(applicationService.getApplicationsByStatus(status));
   
    }

    
	@Operation(
		    summary = "Update application status",
		    description = "Updates the status of an existing job application"
		)
    @PutMapping("/{applicationId}/status/{status}")
    public ResponseEntity<ApplicationResponseDTO> updateStatus(@PathVariable Long applicationId,
                      @PathVariable ApplicationStatus status) {
        return ResponseEntity.ok(applicationService.updateApplicationStatus(applicationId, status));
    
    }

    
	
	@Operation(
		    summary = "Delete application",
		    description = "Deletes an existing job application using the application ID"
		)
    @DeleteMapping("/{applicationId}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long applicationId) {
        applicationService.deleteApplication(applicationId);
        return ResponseEntity.noContent().build();
 
    }

    
	
	@Operation(
		    summary = "Get applications with pagination and sorting",
		    description = "Retrieves applications using page number, page size, sorting field and sorting direction"
		)
    @GetMapping("/pagination")
    public ResponseEntity<Page<ApplicationResponseDTO>> getApplicationsWithPagination(
            @RequestParam int page, @RequestParam int size,
            @RequestParam String sortBy, @RequestParam String direction) {

        Page<ApplicationResponseDTO> applications = applicationService.getApplicationsWithPagination(
                                       page, size, sortBy, direction);
        return ResponseEntity.ok(applications);
    
    }

}


