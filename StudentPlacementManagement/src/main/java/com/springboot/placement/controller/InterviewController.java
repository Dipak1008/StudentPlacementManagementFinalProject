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

import com.springboot.placement.dto.InterviewRequestDTO;
import com.springboot.placement.dto.InterviewResponseDTO;
import com.springboot.placement.enums.InterviewResult;
import com.springboot.placement.service.InterviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/interviews")
@Tag(name = "Interview Management", description = "APIs for managing interviews")
@Validated
public class InterviewController {

	@Autowired
	InterviewService interviewService;

	
	@Operation(
		    summary = "Create interview",
		    description = "Creates a new interview for a job application"
		)
	@PostMapping
	  public ResponseEntity<InterviewResponseDTO> createInterview(@Valid @RequestBody InterviewRequestDTO dto) {
	        InterviewResponseDTO response = interviewService.createInterview(dto);
	        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	   
	}

	
	@Operation(
		    summary = "Get all interviews",
		    description = "Retrieves all interviews"
		)
	@GetMapping
	  public ResponseEntity<List<InterviewResponseDTO>> getAllInterviews() {
       return ResponseEntity.ok(interviewService.getAllInterviews());
	    
	}

	
	
	@Operation(
		    summary = "Get interview by ID",
		    description = "Retrieves interview details using the interview ID"
		)
	@GetMapping("/{interviewId}")
	   public ResponseEntity<InterviewResponseDTO> getInterviewById(@PathVariable Long interviewId) {
	        return ResponseEntity.ok(interviewService.getInterviewById(interviewId));
	
	}
	
	
	@Operation(
		    summary = "Get interviews by result",
		    description = "Retrieves interviews based on their result"
		)
	@GetMapping("/result/{result}")
	   public ResponseEntity<List<InterviewResponseDTO>> getByResult(@PathVariable InterviewResult result) {
        return ResponseEntity.ok(interviewService.getInterviewsByResult(result));
	    
	}

	
	@Operation(
		    summary = "Get interviews by student",
		    description = "Retrieves all interviews related to a specific student"
		)
	@GetMapping("/student/{studentId}")
	    public ResponseEntity<List<InterviewResponseDTO>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(interviewService.getInterviewsByStudent(studentId));
	    
	}

	
	@Operation(
		    summary = "Update interview result",
		    description = "Updates the result of an existing interview"
		)
	@PutMapping("/{interviewId}/result/{result}")
	    public ResponseEntity<InterviewResponseDTO> updateResult(@PathVariable Long interviewId,
	                 @PathVariable InterviewResult result) {
        return ResponseEntity.ok(interviewService.updateInterviewResult(interviewId, result));
	    
	}

	
	
	
	@Operation(
		    summary = "Delete interview",
		    description = "Deletes an existing interview using the interview ID"
		)
	@DeleteMapping("/{interviewId}")
	    public ResponseEntity<Void> deleteInterview(@PathVariable Long interviewId) {
        interviewService.deleteInterview(interviewId);
        return ResponseEntity.noContent().build();

	}
	
	
	@Operation(
		    summary = "Get interviews with pagination and sorting",
		    description = "Retrieves interviews using page number, page size, sorting field and sorting direction"
		)
	@GetMapping("/pagination")
	public ResponseEntity<Page<InterviewResponseDTO>> getInterviewsWithPagination(
	        @RequestParam int page, @RequestParam int size,
	        @RequestParam String sortBy, @RequestParam String direction) {
	    Page<InterviewResponseDTO> interviews = interviewService.getInterviewsWithPagination(
	                         page, size, sortBy, direction);

	    return ResponseEntity.ok(interviews);
	
	}

}

