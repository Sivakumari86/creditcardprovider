package com.creditcard.system.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.creditcard.system.model.CreditCardData;
import com.creditcard.system.model.CreditCardListResponse;

import reactor.core.publisher.Flux;


public interface CardRepository extends ReactiveCrudRepository<CreditCardData, String>{
	//public Flux<CreditCardListResponse> findAll();

}
