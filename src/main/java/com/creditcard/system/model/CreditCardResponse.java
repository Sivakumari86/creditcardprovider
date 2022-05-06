package com.creditcard.system.model;

import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class CreditCardResponse {

    private Long id;
    private String name;
    private String cardNumber;
    private String limit;
    private String balance;
	

}
