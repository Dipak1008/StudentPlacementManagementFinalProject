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

import com.springboot.placement.dto.JobDriveResponseDTO;
import com.springboot.placement.dto.StudentRequestDTO;
import com.springboot.placement.dto.StudentResponseDTO;
import com.springboot.placement.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Management", description = "APIs for managing students")
@Validated
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@Operation(
		    summary = "Create a new student",
		    description = "Registers a new student in the placement management system"
		)
	 @PostMapping
	 public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO dto) {
        StudentResponseDTO response = studentService.createStudent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	 
	 }

	
	@Operation(
		    summary = "Get all students",
		    description = "Retrieves a list of all registered students"
		)
	 @GetMapping
	 public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
	        return ResponseEntity.ok(studentService.getAllStudents());
	 
	 }

	 
	@Operation(
		    summary = "Get student by ID",
		    description = "Retrieves student details using the student ID"
		)
	 @GetMapping("/{studentId}")
	 public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long studentId) {
       return ResponseEntity.ok(studentService.getStudentById(studentId));
    
	 }

	 
	@Operation(
		    summary = "Update student",
		    description = "Updates the details of an existing student"
		)
	@PutMapping("/{studentId}")
	 public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long studentId,
	                @Valid @RequestBody StudentRequestDTO dto) {
      return ResponseEntity.ok(studentService.updateStudent(studentId, dto));
	
	}

	
	@Operation(
		    summary = "Delete student",
		    description = "Deletes an existing student using the student ID"
		)
	@DeleteMapping("/{studentId}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
       studentService.deleteStudent(studentId);
       return ResponseEntity.noContent().build();
	 
	}

	
	@Operation(
		    summary = "Get students by branch",
		    description = "Retrieves students belonging to the specified branch"
		)
	@GetMapping("/branch/{branch}")
	public ResponseEntity<List<StudentResponseDTO>> getByBranch(@PathVariable String branch) {
       return ResponseEntity.ok(studentService.getStudentsByBranch(branch));
	
	}


	
	@Operation(
		    summary = "Get students by CGPA",
		    description = "Retrieves students whose CGPA is greater than or equal to the specified value"
		)

	@GetMapping("/cgpa/{cgpa}")
    public ResponseEntity<List<StudentResponseDTO>> getByCgpa(@PathVariable Double cgpa) {
        return ResponseEntity.ok(studentService.getStudentsByCgpa(cgpa));
	
	}
	
    
	@Operation(
		    summary = "Get students with pagination and sorting",
		    description = "Retrieves students using page number, page size, sorting field and sorting direction"
		)
	@GetMapping("/pagination")
	public ResponseEntity<Page<StudentResponseDTO>> getStudentsWithPagination(
	        @RequestParam int page, @RequestParam int size,
	        @RequestParam String sortBy, @RequestParam String direction) {

	    Page<StudentResponseDTO> students = studentService.getStudentsWithPagination(
	                                page, size, sortBy, direction);
	    return ResponseEntity.ok(students);
	}
	
	
	@PostMapping("/{studentId}/job-drives/{jobDriveId}")
	public ResponseEntity<String> associateJobDrive(@PathVariable Long studentId,
	                           @PathVariable Long jobDriveId) {
	    studentService.associateJobDrive(studentId, jobDriveId);
	    return ResponseEntity.status(HttpStatus.CREATED)
	            .body("Student successfully associated with Job Drive");
	}
	
	
	@GetMapping("/{studentId}/job-drives")
	public ResponseEntity<List<JobDriveResponseDTO>> getJobDrivesByStudentId(
	                       @PathVariable Long studentId) {
	    return ResponseEntity.ok(studentService.getJobDrivesByStudentId(studentId));
	
	}
}	

