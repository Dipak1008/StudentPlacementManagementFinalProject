package com.springboot.placement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.placement.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	List<Student> findByBranch(String branch);
	
	List<Student> findByCgpaGreaterThanEqual(Double cgpa);
	
	List<Student> findByGraduationYear(Integer graduationYear);
 
}
