package com.creditcard.system.service;


import com.creditcard.system.model.CreditCardData;

import reactor.core.publisher.Flux;

public interface CardProcessingService {
    
   

    public Flux<CreditCardData> getAllCards();

    //CreditCardResponse getById(final Long creditCardId);
}
