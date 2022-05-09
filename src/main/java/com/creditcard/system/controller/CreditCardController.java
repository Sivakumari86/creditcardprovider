package com.creditcard.system.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.creditcard.system.mapper.CreditCardMapper;
import com.creditcard.system.model.CreditCardData;
import com.creditcard.system.model.CreditCardRequest;
import com.creditcard.system.service.CardProcessingService;
import com.creditcard.system.utils.CreditCardValidator;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/credit")
public class CreditCardController {
	 @Autowired
	 private CardProcessingService cardProcessingService;
	
		  @Autowired private CreditCardMapper creditCardMapper;
		 
	 
	
	 	@GetMapping("/cards")
	 	@ResponseStatus(value = HttpStatus.OK)
	    public Flux<CreditCardData> getAllCreditCards() {
		 
		 Flux<CreditCardData> data = cardProcessingService.getAllCards();
		 System.out.println("method called" +data.toString());
	        return data;
	    }
	 	
		 @ApiOperation(value = "It will add a new Credit Card")
	     @ApiResponses(value = {
	             @ApiResponse(code = 201, message = "Credit Card created"),
	             @ApiResponse(code = 400, message = "Invalid request fields/ Using the same credit card which is already added")
	     })
		@PostMapping(path = "addCard", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    public Mono<ResponseEntity> createCreditCard1(@Valid @RequestBody final CreditCardRequest creditCardRequest) {
	 		
			 CreditCardData creditCard =
			  creditCardMapper.creditCardRequestToCreditCard(creditCardRequest);
			
			 	Mono<Long> id =	cardProcessingService.addCard(creditCard);
					return id.map(value -> ResponseEntity
							.status(HttpStatus.CREATED)
							.body("card added successfully"));
										
	    }
	 	
		
		  @PostMapping(value="/addCardMono") Mono<CreditCardData> addCardData(@Valid @RequestBody final CreditCardData creditCardData)
		  {
		  System.out.println("add card called.."); Mono<CreditCardData> response = null; 
		  if (CreditCardValidator.luhnCheck(creditCardData.getCardNumber()) &&
		  CreditCardValidator.validateCardDetails(creditCardData)) {
		  System.out.println("card is validated..."); response=
		  cardProcessingService.addCardDetails(creditCardData); } 
		  else {
		  
		  } 
		  return response;
		  }
		 


}
