package com.creditcard.system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.creditcard.system.event.CardEvent;
import com.creditcard.system.model.CreditCardData;
import com.creditcard.system.repo.CardRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CardProcessingServiceImpl implements CardProcessingService {

	
	 @Autowired
	 private CardRepository cardRepository;
  
	 private final ApplicationEventPublisher publisher;
	 CardProcessingServiceImpl(ApplicationEventPublisher publisher, CardRepository cardRepository) {
	        this.publisher = publisher;
	        this.cardRepository = cardRepository;
	    }
    public Flux<CreditCardData> getAllCards() {
    	System.out.println("service method called");
    	return cardRepository.findAll();
    }


	@Override
	public Mono<CreditCardData> addCardDetails(CreditCardData creditCardData) {
		// TODO Auto-generated method stub
		 return cardRepository.save(creditCardData);
				
	}
	@Override
	 public Mono<Long> addCard(CreditCardData creditCardData) {
	        //catalogueItem.setCreatedOn(Instant.now());

	        return
	        		cardRepository
	                .save(creditCardData)
	                .doOnSuccess(item -> publishCardEvent(CardEvent.CARD_CREATED, item))
	                .flatMap(item -> Mono.just(item.getId()));
	    }

	@Override
	public void createCreditCard(CreditCardData creditCard) {
		// TODO Auto-generated method stub
		 cardRepository.save(creditCard);
	}
	private final void publishCardEvent(String eventType, CreditCardData item) {
        this.publisher.publishEvent(new CardEvent(eventType, item));
    }
}
