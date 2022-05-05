package com.creditcard.system.model;

import java.util.List;

public class CreditCardListResponse {
	private List<CreditCardData> creditCards;

    
    public List<CreditCardData> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCardData> creditCards) {
        this.creditCards = creditCards;
    }
}
