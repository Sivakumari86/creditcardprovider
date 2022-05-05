package com.creditcard.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.creditcard.system.model.CreditCardData;
import com.creditcard.system.service.CardProcessingService;


import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/credit")
public class CreditCardController {
	 @Autowired
	 private CardProcessingService cardProcessingService;
	 

	
		/*
		 * @PostMapping(path = "cards", consumes = MediaType.APPLICATION_JSON_VALUE,
		 * produces = MediaType.APPLICATION_JSON_VALUE) public Mono<ResponseEntity>
		 * addCard(@RequestBody CreditCardData request) {
		 * 
		 * Mono<Long> id = catalogueCrudService.addCatalogItem(catalogueItem);
		 * 
		 * return id.map(value -> ResponseEntity.status(HttpStatus.CREATED).body(new
		 * ResourceIdentity(value))).cast(ResponseEntity.class); }
		 * 
		 */ 
	 @GetMapping("/cards")
	   @ResponseStatus(value = HttpStatus.OK)
	    public Flux<CreditCardData> getAllCreditCards() {
		 System.out.println("method called");
	        return cardProcessingService.getAllCards();
	    }


}
