package com.creditcard.system.controller;

import static org.mockito.Mockito.times;

import java.math.BigDecimal;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.creditcard.system.CreditcardproviderApplication;
import com.creditcard.system.mapper.CreditCardMapper;
import com.creditcard.system.model.CreditCardData;
import com.creditcard.system.model.CreditCardRequest;
import com.creditcard.system.repo.CardRepository;
import com.creditcard.system.service.CardProcessingService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j

@SpringBootTest(classes = CreditcardproviderApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
/*
 * @RunWith(SpringRunner.class)
 * 
 * @WebMvcTest(CreditCardController.class)
 */
/*
 * @ExtendWith(SpringExtension.class)
 * 
 * @WebFluxTest(controllers = CreditCardController.class)
 * 
 * @Import(CardProcessingServiceImpl.class)
 */
public class CreditCardControllerTest {

	private static WebTestClient client;
	
	  @Autowired private CardProcessingService cardProcessingServiceMock;
	 
	@MockBean
	CardRepository repository;
	private static final String TEST_PUBLIC_PATH = "/credit/cards";
	
	 @Autowired 
	 private CreditCardController creditCardController;
	 
	@Autowired 
	CreditCardMapper mapper;
	@LocalServerPort
	int port;

	@Autowired
	public void setApplicationContext(ApplicationContext context) {
		System.out.println("Application context initialization");
		this.client = WebTestClient.bindToApplicationContext(context).configureClient()
				.baseUrl("http://localhost:8080/credit").build();
	}

	
	
	  @Test void testCreateEmployee() { CreditCardRequest creditCardRequest =
	  generateCreditCardData(); CreditCardData creditCard =
	  mapper.creditCardRequestToCreditCard(creditCardRequest);
	  
	  
	  Mockito.when(repository.save(creditCard));
	 
	  
	  this.client.post() .uri("/addCard") .contentType(MediaType.APPLICATION_JSON)
	  .body(BodyInserters.fromObject(creditCard)) .exchange()
	  .expectStatus().isCreated();
	  
	  Mockito.verify(repository, times(1)).save(creditCard); }
	 
	
		
		  @Test
		  
		  @Order(10) public void testGetAllCreditCards() {
		  
		  System.out.println("test get All credit cards");
		  this.client.get().uri("/cards").accept(MediaType.APPLICATION_JSON).exchange()
		  .expectStatus().isOk()
		  
		  .expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody().
		  jsonPath ("$.[0].id").isNotEmpty()
		  .jsonPath("$.[0].name").isNotEmpty().jsonPath("$.[0].cardNumber").isNotEmpty(
		  ) .jsonPath("$.[0].limi").isNotEmpty().jsonPath("$.[0].balance").
		  isNotEmpty(); }
		 
	 
	
		
		  @Test
		  
		  @Order(40) public void testCreateCreditCard() { CreditCardRequest
		  creditCardRequest = generateCreditCardData();
		  
		  CreditCardData creditCard =
					  mapper.creditCardRequestToCreditCard(creditCardRequest);
		  this.client .post() .uri("/addCard").contentType(MediaType.APPLICATION_JSON)
		  .body(Mono.just(creditCard), CreditCardData.class) .exchange()
		  .expectStatus().isCreated()
		  .expectHeader().contentType(MediaType.APPLICATION_JSON); }
		 
	 /**
		 * Generate sample credit card request which will be used in test classes
		 *
		 * @return CreditCardRequest
		 */
	private static CreditCardRequest generateCreditCardData() {
		CreditCardData creditCardData = new CreditCardData();
		CreditCardRequest req = new CreditCardRequest();

		req.setName("Shankar");
		req.setCardNumber("4550388325204324");
		req.setLimit(BigDecimal.valueOf(2000));

		return req;
	}

	

}
