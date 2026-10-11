package com.springboot.placement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.placement.entity.Interview;
import com.springboot.placement.enums.InterviewResult;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
	
	List<Interview> findByResult(InterviewResult result);
	
	List<Interview> findByApplicationStudentStudentId(Long studentId);
	

}
