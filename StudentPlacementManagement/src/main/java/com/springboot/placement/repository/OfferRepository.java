package com.springboot.placement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.placement.entity.Offer;
import com.springboot.placement.enums.OfferStatus;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
	
	List<Offer> findByStatus(OfferStatus status);
	
	List<Offer> findByApplicationStudentStudentId(Long studentId);

}
