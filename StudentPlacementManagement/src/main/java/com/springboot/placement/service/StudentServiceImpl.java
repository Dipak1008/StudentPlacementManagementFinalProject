package com.springboot.placement.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.placement.constants.PlacementConstants;
import com.springboot.placement.dto.JobDriveResponseDTO;
import com.springboot.placement.dto.StudentRequestDTO;
import com.springboot.placement.dto.StudentResponseDTO;
import com.springboot.placement.entity.Student;
import com.springboot.placement.exception.ResourceNotFoundException;
import com.springboot.placement.mapper.StudentMapper;
import com.springboot.placement.entity.JobDrive;
import com.springboot.placement.repository.JobDriveRepository;
import com.springboot.placement.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl  implements StudentService{

	private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	JobDriveRepository jobDriveRepository;
	
	@Autowired
	StudentMapper studentMapper;
	
	@Override
	@Transactional
	@CacheEvict(value = "allStudents", allEntries = true)
	public StudentResponseDTO createStudent(StudentRequestDTO dto) {
		log.info("Creating new student with email: {}", dto.getEmail());
		
		Student student = studentMapper.toEntity(dto);
		Student savedStudent = studentRepository.save(student);
		
		log.info("Student created successfully with ID: {}", savedStudent.getStudentId());
		
		return studentMapper.toResponse(savedStudent);
	}

	
	@Override
	@Cacheable(value = "students", key = "#studentId")
	public StudentResponseDTO getStudentById(Long studentId) {
	    log.info("CACHE MISS - Fetching student from database: {}", studentId);

	        Student student = studentRepository.findById(studentId)
	                .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.STUDENT_NOT_FOUND + studentId));

	        return studentMapper.toResponse(student);
	    }

	@Override
	@Cacheable(value = "allStudents")
	public List<StudentResponseDTO> getAllStudents() {
        log.info("Fetching all students");
        return studentRepository.findAll().stream()
                .map(studentMapper :: toResponse).toList();
    }
	
	
	@Override
	@Transactional
	@CacheEvict(value = "allStudents", allEntries = true)
	public StudentResponseDTO updateStudent(Long studentId, StudentRequestDTO dto) {
		log.info("Updating student with ID: {}", studentId);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.STUDENT_NOT_FOUND + studentId));

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setCollegeName(dto.getCollegeName());
        student.setDegree(dto.getDegree());
        student.setBranch(dto.getBranch());
        student.setCgpa(dto.getCgpa());
        student.setGraduationYear(dto.getGraduationYear());

        Student updatedStudent = studentRepository.save(student);
        log.info("Student updated successfully");
        return studentMapper.toResponse(updatedStudent);
    }

	@Override
	@Transactional
	@Caching(evict = {
		    @CacheEvict(value = "students", key = "#studentId"),
		    @CacheEvict(value = "allStudents", allEntries = true)
		})
	public void deleteStudent(Long studentId) {
		 log.info("Deleting student with ID: {}", studentId);
	        if (!studentRepository.existsById(studentId)) {
	            throw new ResourceNotFoundException(PlacementConstants.STUDENT_NOT_FOUND + studentId);
	        }
	        studentRepository.deleteById(studentId);
	        
	        log.info("Student deleted successfully with ID: {}", studentId);
	    }


	@Override
	@Cacheable(value = "studentsByBranch", key = "#branch")
	public List<StudentResponseDTO> getStudentsByBranch(String branch) {
		log.info("Fetching students by branch: {}", branch);
        return studentRepository.findByBranch(branch).stream()
                .map(studentMapper :: toResponse).toList();
    }


	@Override
	@Cacheable(value = "studentsByCgpa", key = "#cgpa")
	public List<StudentResponseDTO> getStudentsByCgpa(Double cgpa) {
		 log.info("Fetching students with CGPA >= {}", cgpa);

	        return studentRepository.findByCgpaGreaterThanEqual(cgpa).stream()
	                .map(studentMapper::toResponse).toList();
	    }

	@Override
	@Cacheable(
            value = "studentsPagination",
            key = "#page + '-' + #size + '-' + #sortBy + '-' + #direction"
    )
	public Page<StudentResponseDTO> getStudentsWithPagination(int page, int size, 
			          String sortBy, String direction) {
		 log.info("Fetching students with pagination");
		    Sort sort;
		    if (direction.equalsIgnoreCase("desc")) {
		        sort = Sort.by(sortBy).descending();
		    } else {
		        sort = Sort.by(sortBy).ascending();
		    }

		    PageRequest pageRequest = PageRequest.of(page, size, sort);
		    Page<Student> students = studentRepository.findAll(pageRequest);
		    return students.map(studentMapper :: toResponse);
		}


	@Override
	@Transactional
	public void associateJobDrive(Long studentId, Long jobDriveId) {
		 Student student = studentRepository.findById(studentId)
		            .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.STUDENT_NOT_FOUND + studentId));

		    JobDrive jobDrive = jobDriveRepository.findById(jobDriveId)
		            .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.JOB_DRIVE_NOT_FOUND + jobDriveId));

		    boolean alreadyAssociated = student.getJobDrives().stream()
		            .anyMatch(drive -> drive.getJobDriveId().equals(jobDriveId));

		    if (alreadyAssociated) {
		        throw new IllegalStateException("This student is already associated with this job drive");
		    }

		    student.getJobDrives().add(jobDrive);
		    studentRepository.save(student);
		}


	@Override
	@Transactional
	public List<JobDriveResponseDTO> getJobDrivesByStudentId(Long studentId) {
		Student student = studentRepository.findById(studentId)
	            .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.STUDENT_NOT_FOUND + studentId));

	    List<JobDriveResponseDTO> response = new ArrayList<>();
	    for (JobDrive jobDrive : student.getJobDrives()) {
	    	
	        JobDriveResponseDTO dto = new JobDriveResponseDTO();
	        dto.setJobDriveId(jobDrive.getJobDriveId());
	        dto.setJobTitle(jobDrive.getJobTitle());
	        dto.setDescription(jobDrive.getDescription());
	        dto.setMinimumCgpa(jobDrive.getMinimumCgpa());
	        dto.setRequiredBranch(jobDrive.getRequiredbranch());
	        dto.setLocation(jobDrive.getLocation());
	        dto.setDriveDate(jobDrive.getDriveDate());
	        dto.setApplicationDeadline(jobDrive.getApplicationDeadline());
	        dto.setStatus(jobDrive.getStatus());

	        if (jobDrive.getCompany() != null) {
	            dto.setCompanyId(jobDrive.getCompany().getCompanyId());
	            dto.setCompanyName(jobDrive.getCompany().getCompanyName());
	        }
	        response.add(dto);
	    }
	    return response;
	}
}