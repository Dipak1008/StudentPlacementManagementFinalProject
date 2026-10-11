package com.springboot.placement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.placement.dto.JobDriveRequestDTO;
import com.springboot.placement.dto.JobDriveResponseDTO;
import com.springboot.placement.enums.JobDriveStatus;
import com.springboot.placement.service.JobDriveService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/job-drives")
@Tag(name = "Job Drive Management", description = "APIs for managing job drives")
@Validated
public class JobDriveController {

	@Autowired
	JobDriveService jobDriveService;
	
	 // Create Job Drive
	@Operation(
		    summary = "Create a new job drive",
		    description = "Creates a new job drive for a company"
		)
    @PostMapping
    public ResponseEntity<JobDriveResponseDTO> createJobDrive(@Valid @RequestBody JobDriveRequestDTO dto) {
        JobDriveResponseDTO response = jobDriveService.createJobDrive(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    
    }

    
    // Get all Job Drives
	@Operation(
		    summary = "Get all job drives",
		    description = "Retrieves all available job drives"
		)
    @GetMapping
    public ResponseEntity<List<JobDriveResponseDTO>> getAllJobDrives() {
        return ResponseEntity.ok(jobDriveService.getAllJobDrives());
    
    }

    
	
    // Get Job Drive by ID
	@Operation(
		    summary = "Get job drive by ID",
		    description = "Retrieves job drive details using the job drive ID"
		)
    @GetMapping("/{jobDriveId}")
    public ResponseEntity<JobDriveResponseDTO> getJobDriveById(@PathVariable Long jobDriveId) {
        return ResponseEntity.ok(jobDriveService.getJobDriveById(jobDriveId));
    
    }

    
    // Update Job Drive
	@Operation(
		    summary = "Update job drive",
		    description = "Updates the details of an existing job drive"
		)
    @PutMapping("/{jobDriveId}")
    public ResponseEntity<JobDriveResponseDTO> updateJobDrive(@PathVariable Long jobDriveId,
                       @Valid @RequestBody JobDriveRequestDTO dto) {
       return ResponseEntity.ok(jobDriveService.updateJobDrive(jobDriveId, dto));
    
    }

    
	
    // Delete Job Drive
    @DeleteMapping("/{jobDriveId}")
    @Operation(
    	    summary = "Delete job drive",
    	    description = "Deletes an existing job drive using the job drive ID"
    	)
    public ResponseEntity<Void> deleteJobDrive(@PathVariable Long jobDriveId) {
        jobDriveService.deleteJobDrive(jobDriveId);
        return ResponseEntity.noContent().build();

    }

    
        
    // Get Job Drives by status
    @Operation(
    	    summary = "Get job drives by status",
    	    description = "Retrieves job drives based on their status such as OPEN or CLOSED"
    	)
    @GetMapping("/status/{status}")
    public ResponseEntity<List<JobDriveResponseDTO>> getByStatus(@PathVariable JobDriveStatus status) {
        return ResponseEntity.ok(jobDriveService.getJobDrivesByStatus(status));
    
    }

    
    
    // Get Job Drives by location
    @Operation(
    	    summary = "Get job drives by location",
    	    description = "Retrieves job drives available at the specified location"
    	)
    @GetMapping("/location/{location}")
    public ResponseEntity<List<JobDriveResponseDTO>> getByLocation(@PathVariable String location) {
        return ResponseEntity.ok(jobDriveService.getJobDrivesByLocation(location));
    
    }

    
    // Get eligible Job Drives by CGPA
    @Operation(
    	    summary = "Get eligible job drives by CGPA",
    	    description = "Retrieves job drives for which the specified CGPA meets the minimum eligibility requirement"
    	)
    @GetMapping("/eligible/{cgpa}")
    public ResponseEntity<List<JobDriveResponseDTO>> getEligibleJobDrives(@PathVariable Double cgpa) {
        return ResponseEntity.ok(jobDriveService.getEligibleJobDrives(cgpa));
    
    }

    
    // Close Job Drive
    @Operation(
    	    summary = "Close job drive",
    	    description = "Closes an open job drive so that new applications cannot be submitted"
    	)
    @PatchMapping("/{jobDriveId}/close")
    public ResponseEntity<JobDriveResponseDTO> closeJobDrive(@PathVariable Long jobDriveId) {
        return ResponseEntity.ok(jobDriveService.closeJobDrive(jobDriveId));

    }
    
    
    @Operation(
    	    summary = "Get job drives with pagination and sorting",
    	    description = "Retrieves job drives using page number, page size, sorting field and sorting direction"
    	)
    @GetMapping("/pagination")
    public ResponseEntity<Page<JobDriveResponseDTO>> getJobDrivesWithPagination(
            @RequestParam int page, @RequestParam int size,
            @RequestParam String sortBy, @RequestParam String direction) {

        Page<JobDriveResponseDTO> jobDrives = jobDriveService.getJobDrivesWithPagination(
                               page, size, sortBy, direction);
        return ResponseEntity.ok(jobDrives);
    }
}

