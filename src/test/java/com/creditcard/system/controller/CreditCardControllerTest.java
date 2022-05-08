package com.creditcard.system.controller;

import java.math.BigDecimal;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.creditcard.system.CreditcardproviderApplication;
import com.creditcard.system.model.CreditCardData;
import com.creditcard.system.model.CreditCardRequest;
import com.creditcard.system.repo.CardRepository;
import com.creditcard.system.service.CardProcessingService;

import javafx.application.Application;
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
public class CreditCardControllerTest {

	private static WebTestClient client;
	@Autowired
	private CardProcessingService cardProcessingServiceMock;
	@MockBean
	CardRepository repository;
	private static final String TEST_PUBLIC_PATH = "/credit/cards";
	@Autowired
	private CreditCardController creditCardController;
	@LocalServerPort
	int port;

	@Autowired
	public void setApplicationContext(ApplicationContext context) {
		System.out.println("Application context initialization");
		this.client = WebTestClient.bindToApplicationContext(context).configureClient()
				.baseUrl("http://localhost:8080/credit").build();
	}

	
	  @Test
	  
	  @Order(10) public void testGetAllCreditCards() {
	  
	  System.out.println("test get All credit cards");
	  this.client.get().uri("/cards").accept(MediaType.APPLICATION_JSON).exchange()
	  .expectStatus().isOk()
	  
	  .expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody().
	  jsonPath
	  ("$.[0].id").isNotEmpty()
	  .jsonPath("$.[0].name").isNotEmpty().jsonPath("$.[0].cardNumber").isNotEmpty(
	  ) .jsonPath("$.[0].creditLimit").isNotEmpty().jsonPath("$.[0].balance").
	  isNotEmpty(); }
	 
	
	/*
	 * @Test
	 * 
	 * @Order(40) public void testCreateCreditCard() { CreditCardRequest
	 * creditCardRequest = generateCreditCardData();
	 * 
	 * 
	 * this.client .post() .uri("/addCard").contentType(MediaType.APPLICATION_JSON)
	 * .body(Mono.just(creditCardRequest), CreditCardRequest.class) .exchange()
	 * .expectStatus().isCreated()
	 * .expectHeader().contentType(MediaType.APPLICATION_JSON); }
	 */
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

	/*
	 * @Test public void testCreateCreditCard1() throws Exception { final
	 * List<String> validCardNumbers = Arrays.asList("4003600000000014",
	 * "1358954993914435", "12345678903555"); for (final String cc :
	 * validCardNumbers) { final CreditCardRequest creditCardRequest =
	 * CreditCardRequest.builder().cardNumber(cc).limit(BigDecimal.valueOf(400)).
	 * name("random").build(); final CreditCard creditCard =
	 * CreditCard.builder().cardNumber(cc).limit(BigDecimal.valueOf(400)).name(
	 * "random").balance(BigDecimal.valueOf(0.0)).build(); final CreditCard
	 * creditCardAfterSavedInDb =
	 * CreditCard.builder().id(1L).cardNumber(cc).limit(BigDecimal.valueOf(400)).
	 * name("random").balance(BigDecimal.valueOf(0.0)).build();
	 * when(creditCardMapperMock.creditCardRequestToCreditCard(any(CreditCardRequest
	 * .class))).thenReturn(creditCard);
	 * when(cardProcessingServiceMock.createCreditCard(creditCard)).thenReturn(
	 * creditCardAfterSavedInDb); final MvcResult mvcResult =
	 * mockMvc.perform(MockMvcRequestBuilders.post(TEST_PUBLIC_PATH)
	 * .contentType(MediaType.APPLICATION_JSON)
	 * .content(objMapper.writeValueAsString(creditCardRequest)))
	 * .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
	 * assertThat(mvcResult.getResponse().getHeader("location")).contains(
	 * TEST_PUBLIC_PATH + "/1"); } //Todo: ArgumentCaptor can be used to verify
	 * actual value verify(creditCardMapperMock,
	 * times(3)).creditCardRequestToCreditCard(any(CreditCardRequest.class));
	 * verify(cardProcessingServiceMock,
	 * times(3)).createCreditCard(any(CreditCard.class)); }
	 */

}
