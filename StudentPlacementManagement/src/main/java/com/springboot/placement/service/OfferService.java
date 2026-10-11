package com.springboot.placement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.placement.dto.OfferRequestDTO;
import com.springboot.placement.dto.OfferResponseDTO;
import com.springboot.placement.enums.OfferStatus;

public interface OfferService {

	 OfferResponseDTO createOffer(OfferRequestDTO dto);

	 OfferResponseDTO getOfferById(Long offerId);

	 List<OfferResponseDTO> getAllOffers();

	 List<OfferResponseDTO> getOffersByStatus(OfferStatus status);

     List<OfferResponseDTO> getOffersByStudent(Long studentId);

     OfferResponseDTO updateOfferStatus(Long offerId, OfferStatus status);

     void deleteOffer(Long offerId);
	
     Page<OfferResponseDTO> getOffersWithPagination(int page, int size, String sortBy, String direction);


}  
