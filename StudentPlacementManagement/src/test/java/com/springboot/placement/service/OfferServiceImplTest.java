package com.springboot.placement.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springboot.placement.dto.OfferResponseDTO;
import com.springboot.placement.entity.Offer;
import com.springboot.placement.mapper.OfferMapper;
import com.springboot.placement.repository.ApplicationRepository;
import com.springboot.placement.repository.OfferRepository;

@ExtendWith(MockitoExtension.class)
public class OfferServiceImplTest {

	@Mock
    private OfferRepository offerRepository;

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private OfferMapper offerMapper;

    @InjectMocks
    private OfferServiceImpl offerService;

    @Test
    void testGetOfferById_Success() {
        Offer offer = new Offer();
        OfferResponseDTO responseDTO = new OfferResponseDTO();
        when(offerRepository.findById(1L)).thenReturn(Optional.of(offer));
        when(offerMapper.toResponse(offer)).thenReturn(responseDTO);
        OfferResponseDTO result = offerService.getOfferById(1L);
        assertNotNull(result);
        verify(offerRepository).findById(1L);
        verify(offerMapper).toResponse(offer);
    }

    @Test
    void testDeleteOffer_Success() {
        when(offerRepository.existsById(1L)).thenReturn(true);
        assertDoesNotThrow(() -> offerService.deleteOffer(1L));
        verify(offerRepository).existsById(1L);
        verify(offerRepository).deleteById(1L);
    }
}


