package com.springboot.placement.mapper;

import org.springframework.stereotype.Component;

import com.springboot.placement.dto.CompanyRequestDTO;
import com.springboot.placement.dto.CompanyResponseDTO;
import com.springboot.placement.entity.Company;

@Component
public class CompanyMapper {

	public Company toEntity(CompanyRequestDTO dto) {
		Company company = new Company();
		
		company.setCompanyName(dto.getCompanyName());
		company.setEmail(dto.getEmail());
		company.setPhone(dto.getPhone());
		company.setLocation(dto.getLocation());
		company.setIndustry(dto.getIndustry());
		company.setRole(dto.getRole());
		
		
		return company;
		
	}
	
	
	public CompanyResponseDTO toResponse(Company company) {
		
		CompanyResponseDTO dto = new CompanyResponseDTO();
		
		dto.setCompanyId(company.getCompanyId());
		dto.setCompanyName(company.getCompanyName());
		dto.setEmail(company.getEmail());
		dto.setPhone(company.getPhone());
		dto.setLocation(company.getLocation());
		dto.setIndustry(company.getIndustry());
		dto.setRole(company.getRole());
		
		return dto;
		
		
	}
}
