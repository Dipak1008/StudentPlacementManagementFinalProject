package com.springboot.placement.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.placement.constants.PlacementConstants;
import com.springboot.placement.dto.CompanyRequestDTO;
import com.springboot.placement.dto.CompanyResponseDTO;
import com.springboot.placement.entity.Company;
import com.springboot.placement.exception.ResourceNotFoundException;
import com.springboot.placement.mapper.CompanyMapper;
import com.springboot.placement.repository.CompanyRepository;

import jakarta.transaction.Transactional;

@Service
public class CompanyServiceImpl implements CompanyService {

	private static final Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);

	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
    CompanyMapper companyMapper;

	
	@Override
	@Transactional
	public CompanyResponseDTO createCompany(CompanyRequestDTO dto) {
		 log.info("Creating company: {}", dto.getCompanyName());
	        Company company = companyMapper.toEntity(dto);
	        Company savedCompany = companyRepository.save(company);
	     log.info("Company created successfully with ID: {}", savedCompany.getCompanyId());

	        return companyMapper.toResponse(savedCompany);
	    }


	@Override
	public CompanyResponseDTO getCompanyById(Long companyId) {
		log.info("Fetching company with ID: {}", companyId);

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.COMPANY_NOT_FOUND + companyId));
        return companyMapper.toResponse(company);
    }


	@Override
	public List<CompanyResponseDTO> getAllCompanies() {
        log.info("Fetching all companies");
        return companyRepository.findAll().stream().map(companyMapper::toResponse).toList();
    }

	
	@Override
	@Transactional
	public CompanyResponseDTO updateCompany(Long companyId, CompanyRequestDTO dto) {
		  log.info("Updating company with ID: {}", companyId);

	        Company company = companyRepository.findById(companyId)
	                .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.COMPANY_NOT_FOUND + companyId));

	        company.setCompanyName(dto.getCompanyName());
	        company.setEmail(dto.getEmail());
	        company.setPhone(dto.getPhone());
	        company.setLocation(dto.getLocation());
	        company.setIndustry(dto.getIndustry());

	        Company updatedCompany = companyRepository.save(company);
	        log.info("Company updated successfully");

	        return companyMapper.toResponse(updatedCompany);
	    }


	@Override
	@Transactional
	public void deleteCompany(Long companyId) {
		 log.info("Deleting company with ID: {}", companyId);
	        if (!companyRepository.existsById(companyId)) {
	            throw new ResourceNotFoundException(PlacementConstants.COMPANY_NOT_FOUND + companyId);
	        }

	        companyRepository.deleteById(companyId);

	        log.info("Company deleted successfully with ID: {}", companyId);
	    }
	
	
	@Override
	public List<CompanyResponseDTO> getCompaniesByIndustry(String industry) {
		 log.info("Fetching companies by industry: {}", industry);

	        return companyRepository.findByIndustry(industry).stream()
	                .map(companyMapper :: toResponse).toList();
	    }
	
	
	@Override
	public List<CompanyResponseDTO> getCompaniesByLocation(String location) {
		log.info("Fetching companies by location: {}", location);
        return companyRepository.findByLocation(location).stream()
                .map(companyMapper :: toResponse).toList();
    }


	@Override
	public Page<CompanyResponseDTO> getCompaniesWithPagination(int page, int size, String sortBy, String direction) {
		 log.info("Fetching companies with pagination");

		    Sort sort;
		    if (direction.equalsIgnoreCase("desc")) {
		        sort = Sort.by(sortBy).descending();
		    } else {
		        sort = Sort.by(sortBy).ascending();
		    }

		    PageRequest pageRequest = PageRequest.of(page, size, sort);
		    Page<Company> companies = companyRepository.findAll(pageRequest);

		    return companies.map(companyMapper::toResponse);
		}
	

}
