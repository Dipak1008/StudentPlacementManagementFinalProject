package com.springboot.placement.mapper;

import org.springframework.stereotype.Component;

import com.springboot.placement.dto.OfferResponseDTO;
import com.springboot.placement.entity.Offer;

@Component
public class OfferMapper {

  public OfferResponseDTO toResponse(Offer offer) {
	  OfferResponseDTO dto = new OfferResponseDTO();
      dto.setOfferId(offer.getOfferId());
      dto.setOfferDate(offer.getOfferDate());
      dto.setSalary(offer.getSalary());
      dto.setRequiredbranch(offer.getRequiredBranch());
      dto.setJoiningDate(offer.getJoiningDate());
      dto.setStatus(offer.getStatus());

      if (offer.getApplication() != null) {
          dto.setApplicationId(offer.getApplication().getApplicationId());

          if (offer.getApplication().getStudent() != null) {
              dto.setStudentId(offer.getApplication().getStudent().getStudentId());
              dto.setStudentName(offer.getApplication().getStudent().getName());
          }

          if (offer.getApplication().getJobDrive() != null) {
              dto.setJobDriveId(offer.getApplication().getJobDrive().getJobDriveId());
              dto.setJobTitle(offer.getApplication().getJobDrive().getJobTitle());

              if (offer.getApplication().getJobDrive().getCompany() != null) {
                  dto.setCompanyId(offer.getApplication().getJobDrive().getCompany().getCompanyId());
                  dto.setCompanyName(offer.getApplication().getJobDrive().getCompany().getCompanyName());
              }
          }
      }

      return dto;
  }
}


	       