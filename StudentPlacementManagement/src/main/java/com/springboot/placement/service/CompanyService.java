package com.springboot.placement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.placement.dto.CompanyRequestDTO;
import com.springboot.placement.dto.CompanyResponseDTO;

public interface CompanyService {

	CompanyResponseDTO createCompany(CompanyRequestDTO dto);

    CompanyResponseDTO getCompanyById(Long companyId);

    List<CompanyResponseDTO> getAllCompanies();

    CompanyResponseDTO updateCompany(Long companyId, CompanyRequestDTO dto);

    void deleteCompany(Long companyId);

    List<CompanyResponseDTO> getCompaniesByIndustry(String industry);

    List<CompanyResponseDTO> getCompaniesByLocation(String location);

    Page<CompanyResponseDTO> getCompaniesWithPagination(int page, int size, String sortBy, String direction);
}
