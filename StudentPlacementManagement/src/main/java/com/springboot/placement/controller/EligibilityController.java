package com.springboot.placement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.placement.dto.EligibilityResponseDTO;
import com.springboot.placement.service.ApplicationService;

@RestController
@RequestMapping("/api/eligibility")
public class EligibilityController {
       
	@Autowired
	  ApplicationService service;
	   
	  @GetMapping("/{studentId}/{jobDriveId}")
	   public ResponseEntity<EligibilityResponseDTO> check(@PathVariable Long studentId, @PathVariable Long jobDriveId) {
	        
		  return ResponseEntity.ok(service.checkEligibility(studentId, jobDriveId));
	    }
}
