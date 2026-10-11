package com.springboot.placement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.placement.entity.Application;
import com.springboot.placement.enums.ApplicationStatus;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
 
	List<Application> findByStatus(ApplicationStatus status);
	
	List<Application> findByStudentStudentId(Long studentId);
	
	List<Application> findByJobDriveJobDriveId(Long jobDriveId);
	
	boolean existsByStudentStudentIdAndJobDriveJobDriveId(Long studentId, Long jobDriveId);

    long countByStatus(ApplicationStatus status);
	
}
