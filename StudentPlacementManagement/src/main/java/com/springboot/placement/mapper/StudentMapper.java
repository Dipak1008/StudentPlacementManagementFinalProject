package com.springboot.placement.mapper;

import org.springframework.stereotype.Component;

import com.springboot.placement.dto.StudentRequestDTO;
import com.springboot.placement.dto.StudentResponseDTO;
import com.springboot.placement.entity.Student;

@Component
public class StudentMapper {

	public Student toEntity(StudentRequestDTO dto) {
		
		Student student = new Student();
		
		student.setName(dto.getName());
		student.setEmail(dto.getEmail());
		student.setPhone(dto.getPhone());
		student.setCollegeName(dto.getCollegeName());
		student.setDegree(dto.getDegree());
		student.setBranch(dto.getBranch());
		student.setCgpa(dto.getCgpa());
		student.setGraduationYear(dto.getGraduationYear());
		student.setRole(dto.getRole());
		
		return student ;
		
	}
	
	
	public StudentResponseDTO toResponse(Student student) {
		
		StudentResponseDTO dto = new StudentResponseDTO();
		
		dto.setStudentId(student.getStudentId());
		dto.setName(student.getName());
		dto.setEmail(student.getEmail());
		dto.setPhone(student.getPhone());
		dto.setCollegeName(student.getCollegeName());
		dto.setCgpa(student.getCgpa());
		dto.setDegree(student.getDegree());
		dto.setBranch(student.getBranch());
		dto.setGraduationYear(student.getGraduationYear());
		dto.setRole(student.getRole());
		
		
		return dto;
		
	}
	
	
}
