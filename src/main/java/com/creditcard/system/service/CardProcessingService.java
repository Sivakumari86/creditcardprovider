package com.creditcard.system.service;


import com.creditcard.system.model.CreditCardData;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CardProcessingService {
    
   

    public Flux<CreditCardData> getAllCards();

	public Mono<CreditCardData> addCardDetails(CreditCardData creditCardData);

	public void createCreditCard(CreditCardData creditCard);

	public Mono<Long> addCard(CreditCardData creditCardData);

    //CreditCardResponse getById(final Long creditCardId);
}
