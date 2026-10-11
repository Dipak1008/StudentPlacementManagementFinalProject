package com.springboot.placement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.placement.entity.JobDrive;
import com.springboot.placement.enums.JobDriveStatus;

@Repository
public interface JobDriveRepository extends JpaRepository<JobDrive, Long> {
	
	List<JobDrive> findByStatus(JobDriveStatus status);
	
	List<JobDrive> findByLocation(String location);
	
	@Query("SELECT j FROM JobDrive j WHERE j.minimumCgpa <= :cgpa")
	List<JobDrive> findEligibleDrives(@Param("cgpa") Double cgpa);

}
