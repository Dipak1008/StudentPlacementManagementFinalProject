package com.springboot.placement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.placement.dto.CompanyRequestDTO;
import com.springboot.placement.dto.CompanyResponseDTO;
import com.springboot.placement.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/companies")
@Tag(name = "Company Management", description = "APIs for managing companies")
@Validated
public class CompanyController {

	@Autowired
	CompanyService companyService;
	
	
	@Operation(
		    summary = "Create a new company",
		    description = "Registers a new company in the placement management system"
		)
	@PostMapping
    public ResponseEntity<CompanyResponseDTO> createCompany(@Valid @RequestBody CompanyRequestDTO dto) {
        CompanyResponseDTO response = companyService.createCompany(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

	
	@Operation(
		    summary = "Get all companies",
		    description = "Retrieves a list of all registered companies"
		)
    @GetMapping
    public ResponseEntity<List<CompanyResponseDTO>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    
	}

	
	@Operation(
		    summary = "Get company by ID",
		    description = "Retrieves company details using the company ID"
		)
    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyResponseDTO> getCompanyById(@PathVariable Long companyId) {
        return ResponseEntity.ok(companyService.getCompanyById(companyId));
    
	}

	
	@Operation(
		    summary = "Update company",
		    description = "Updates the details of an existing company"
		)

    @PutMapping("/{companyId}")
    public ResponseEntity<CompanyResponseDTO> updateCompany( @PathVariable Long companyId,
                     @Valid @RequestBody CompanyRequestDTO dto) {
        return ResponseEntity.ok(companyService.updateCompany(companyId, dto));
   
	}

	
	
	@Operation(
		    summary = "Delete company",
		    description = "Deletes an existing company using the company ID"
		)

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);
        return ResponseEntity.noContent().build();
    
	}

	
	@Operation(
		    summary = "Get companies by industry",
		    description = "Retrieves companies belonging to the specified industry"
		)
    @GetMapping("/industry/{industry}")
    public ResponseEntity<List<CompanyResponseDTO>> getByIndustry(@PathVariable String industry) {
        return ResponseEntity.ok(companyService.getCompaniesByIndustry(industry));
    
	}

	
	
	@Operation(
		    summary = "Get companies by location",
		    description = "Retrieves companies located in the specified location"
		)
    @GetMapping("/location/{location}")
    public ResponseEntity<List<CompanyResponseDTO>> getByLocation(@PathVariable String location) {
        return ResponseEntity.ok(companyService.getCompaniesByLocation(location));
    
	}

    
	@Operation(
		    summary = "Get companies with pagination and sorting",
		    description = "Retrieves companies using page number, page size, sorting field and sorting direction"
		)
    @GetMapping("/pagination")
    public ResponseEntity<Page<CompanyResponseDTO>> getCompaniesWithPagination(
            @RequestParam int page, @RequestParam int size,
            @RequestParam String sortBy, @RequestParam String direction) {

        Page<CompanyResponseDTO> companies = companyService.getCompaniesWithPagination(
                                 page, size, sortBy, direction);
        return ResponseEntity.ok(companies);
    }
}
