package com.springboot.placement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.placement.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	List<Company> findByIndustry(String industry);
	
	List<Company> findByLocation(String loacation);

}
