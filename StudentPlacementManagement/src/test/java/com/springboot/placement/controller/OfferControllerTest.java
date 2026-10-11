package com.springboot.placement.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.springboot.placement.dto.OfferResponseDTO;
import com.springboot.placement.service.OfferService;

@WebMvcTest(OfferController.class)
public class OfferControllerTest {

	   @Autowired
	    private MockMvc mockMvc;

	    @MockitoBean
	    private OfferService offerService;

	    @MockitoBean
	    private CacheManager cacheManager;

	    @Test
	    void testGetAllOffers_Success() throws Exception {
	        OfferResponseDTO responseDTO = new OfferResponseDTO();
	        when(offerService.getAllOffers()).thenReturn(List.of(responseDTO));
	        mockMvc.perform(get("/api/offers")).andExpect(status().isOk());
	        verify(offerService).getAllOffers();
	    }

	    @Test
	    void testDeleteOffer_Success() throws Exception {
	        doNothing().when(offerService).deleteOffer(1L);
	        mockMvc.perform(delete("/api/offers/1")).andExpect(status().isNoContent());
	        verify(offerService).deleteOffer(1L);
	    }
	
}
