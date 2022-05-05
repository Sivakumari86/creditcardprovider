package com.creditcard.system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditcard.system.model.CreditCardData;
import com.creditcard.system.repo.CardRepository;

import reactor.core.publisher.Flux;

@Service
public class CardProcessingServiceImpl implements CardProcessingService {

	
	 @Autowired
	 private CardRepository cardRepository;
  

    public Flux<CreditCardData> getAllCards() {
    	System.out.println("service method called");
    	return cardRepository.findAll();
    }
}
