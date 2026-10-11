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

import com.springboot.placement.dto.OfferRequestDTO;
import com.springboot.placement.dto.OfferResponseDTO;
import com.springboot.placement.enums.OfferStatus;
import com.springboot.placement.service.OfferService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/offers")
@Tag(name = "Offer Management", description = "APIs for managing offers")
@Validated
public class OfferController {
	
	@Autowired
	OfferService offerService;
	
	
	@Operation(
		    summary = "Create offer",
		    description = "Creates a new job offer for a selected application"
		)
	@PostMapping
    public ResponseEntity<OfferResponseDTO> createOffer(@Valid @RequestBody OfferRequestDTO dto) {
        OfferResponseDTO response = offerService.createOffer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    
	}

	
	@Operation(
		    summary = "Get all offers",
		    description = "Retrieves all job offers"
		)
    @GetMapping
    public ResponseEntity<List<OfferResponseDTO>> getAllOffers() {
        return ResponseEntity.ok(offerService.getAllOffers());
  
    }

    
	@Operation(
		    summary = "Get offer by ID",
		    description = "Retrieves offer details using the offer ID"
		)	
    @GetMapping("/{offerId}")
    public ResponseEntity<OfferResponseDTO> getOfferById(@PathVariable Long offerId) {
        return ResponseEntity.ok(offerService.getOfferById(offerId));
    
    }

    
	@Operation(
		    summary = "Get offers by status",
		    description = "Retrieves offers based on their current status"
		)
    @GetMapping("/status/{status}")
    public ResponseEntity<List<OfferResponseDTO>> getByStatus(@PathVariable OfferStatus status) {
        return ResponseEntity.ok(offerService.getOffersByStatus(status));
    
    }

    
	
	@Operation(
		    summary = "Get offers by student",
		    description = "Retrieves all job offers received by a specific student"
		)
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<OfferResponseDTO>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(offerService.getOffersByStudent(studentId));
    
    }

    
	
	@Operation(
		    summary = "Update offer status",
		    description = "Updates the status of an existing job offer"
		)
    @PutMapping("/{offerId}/status/{status}")
    public ResponseEntity<OfferResponseDTO> updateStatus(@PathVariable Long offerId,
    		      @PathVariable OfferStatus status) {
        return ResponseEntity.ok(offerService.updateOfferStatus(offerId, status));
   
    }

    
	@Operation(
		    summary = "Delete offer",
		    description = "Deletes an existing job offer using the offer ID"
		)
    @DeleteMapping("/{offerId}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long offerId) {
        offerService.deleteOffer(offerId);
        return ResponseEntity.noContent().build();
    
    }

    
	
	@Operation(
		    summary = "Get offers with pagination and sorting",
		    description = "Retrieves offers using page number, page size, sorting field and sorting direction"
		)
    @GetMapping("/pagination")
    public ResponseEntity<Page<OfferResponseDTO>> getOffersWithPagination(
            @RequestParam int page, @RequestParam int size,
            @RequestParam String sortBy, @RequestParam String direction) {
        Page<OfferResponseDTO> offers = offerService.getOffersWithPagination(
                                    page, size, sortBy, direction);

        return ResponseEntity.ok(offers);

    }
}
