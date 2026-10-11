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
import com.springboot.placement.dto.OfferRequestDTO;
import com.springboot.placement.dto.OfferResponseDTO;
import com.springboot.placement.entity.Application;
import com.springboot.placement.entity.Offer;
import com.springboot.placement.enums.OfferStatus;
import com.springboot.placement.exception.ResourceNotFoundException;
import com.springboot.placement.mapper.OfferMapper;
import com.springboot.placement.repository.ApplicationRepository;
import com.springboot.placement.repository.OfferRepository;

import jakarta.transaction.Transactional;

@Service
public class OfferServiceImpl implements OfferService {

	 private static final Logger log = LoggerFactory.getLogger(OfferServiceImpl.class);

	 @Autowired
	 OfferRepository offerRepository;
	 
	 @Autowired
     ApplicationRepository applicationRepository;
	 
	 @Autowired
     OfferMapper offerMapper;

	 
	@Override
	@Transactional
	public OfferResponseDTO createOffer(OfferRequestDTO dto) {
	 
		log.info("Creating offer for application ID: {}", dto.getApplicationId());

	        Application application = applicationRepository.findById(dto.getApplicationId())
	                .orElseThrow(() -> new ResourceNotFoundException(PlacementConstants.APPLICATION_NOT_FOUND
	                                + dto.getApplicationId()));

	        Offer offer = new Offer();
	        offer.setOfferDate(dto.getOfferDate());
	        offer.setSalary(dto.getSalary());
	        offer.setRequiredBranch(dto.getRequiredbranch());
	        offer.setJoiningDate(dto.getJoiningDate());

	        if (dto.getStatus() != null) {
	            offer.setStatus(dto.getStatus());
	        } else {
	            offer.setStatus(OfferStatus.PENDING);
	      }

	        offer.setApplication(application);
	        Offer savedOffer = offerRepository.save(offer);

	        log.info("Offer created successfully with ID: {}", savedOffer.getOfferId());
	        return offerMapper.toResponse(savedOffer);
	    }

	
	
	@Override
	public OfferResponseDTO getOfferById(Long offerId) {
		 log.info("Fetching offer with ID: {}", offerId);

	        Offer offer = offerRepository.findById(offerId)
	                .orElseThrow(() -> new ResourceNotFoundException (PlacementConstants.OFFER_NOT_FOUND + offerId));
	        return offerMapper.toResponse(offer);
	    }


	@Override
	public List<OfferResponseDTO> getAllOffers() {
        log.info("Fetching all offers");
        return offerRepository.findAll().stream().map(offerMapper::toResponse).toList();
    
	}

	
	
	@Override
	public List<OfferResponseDTO> getOffersByStatus(OfferStatus status) {
		 log.info("Fetching offers by status: {}", status);
         return offerRepository.findByStatus(status).stream().map(offerMapper::toResponse).toList();
	    
	}
	
	
	@Override
	public List<OfferResponseDTO> getOffersByStudent(Long studentId) {
		 log.info("Fetching offers for student ID: {}", studentId);
         return offerRepository.findByApplicationStudentStudentId(studentId)
	                .stream().map(offerMapper::toResponse).toList();
	    
	}
	
	
	@Override
	@Transactional
	public OfferResponseDTO updateOfferStatus(Long offerId, OfferStatus status) {
		log.info("Updating offer ID: {} with status: {}", offerId, status);

        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new ResourceNotFoundException (PlacementConstants.OFFER_NOT_FOUND + offerId));

        offer.setStatus(status);
        Offer updatedOffer = offerRepository.save(offer);

        log.info("Offer status updated successfully");
        return offerMapper.toResponse(updatedOffer);

	}

	
	@Override
	@Transactional
	public void deleteOffer(Long offerId) {
		log.info("Deleting offer with ID: {}", offerId);

        if (!offerRepository.existsById(offerId)) {
            throw new ResourceNotFoundException (PlacementConstants.OFFER_NOT_FOUND + offerId);
        }

        offerRepository.deleteById(offerId);
        log.info("Offer deleted successfully with ID: {}", offerId);
    
	}



	@Override
	public Page<OfferResponseDTO> getOffersWithPagination(int page, int size, String sortBy, String direction) {
		log.info("Fetching offers with pagination");

		 Sort sort;
		    if (direction.equalsIgnoreCase("desc")) {
		        sort = Sort.by(sortBy).descending();
		    } else {
		        sort = Sort.by(sortBy).ascending();
		    
		    }

		    PageRequest pageRequest = PageRequest.of(page, size, sort);
		    Page<Offer> offers = offerRepository.findAll(pageRequest);
		    return offers.map(offerMapper::toResponse);
		}

}