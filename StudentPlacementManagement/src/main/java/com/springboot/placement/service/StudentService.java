package com.springboot.placement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.placement.dto.JobDriveResponseDTO;
import com.springboot.placement.dto.StudentRequestDTO;
import com.springboot.placement.dto.StudentResponseDTO;

public interface StudentService {
	
	StudentResponseDTO createStudent(StudentRequestDTO dto);
	
	StudentResponseDTO getStudentById(Long studentId);
	
	List<StudentResponseDTO> getAllStudents();
	
	StudentResponseDTO updateStudent(Long studentId, StudentRequestDTO dto);
	
	void deleteStudent(Long studentId);
	
	List<StudentResponseDTO> getStudentsByBranch(String branch);
	
	List<StudentResponseDTO> getStudentsByCgpa(Double cgpa);
	
	Page<StudentResponseDTO> getStudentsWithPagination(int page, int size, String sortBy, String direction);

	void associateJobDrive(Long studentId, Long jobDriveId);
	
	List<JobDriveResponseDTO> getJobDrivesByStudentId(Long studentId);
}
