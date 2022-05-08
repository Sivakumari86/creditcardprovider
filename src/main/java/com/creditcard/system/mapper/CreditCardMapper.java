package com.creditcard.system.mapper;

import java.math.BigDecimal;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import com.creditcard.system.config.ServerConstants;
import com.creditcard.system.model.CreditCardData;
import com.creditcard.system.model.CreditCardRequest;
import com.creditcard.system.model.CreditCardResponse;

@Component
public class CreditCardMapper {

	public  CreditCardData creditCardRequestToCreditCard(CreditCardRequest creditCardRequest) {
		 if ( creditCardRequest == null ) {
	            return null;
	        }

		 CreditCardData creditCard = new CreditCardData();

	        creditCard.setCardNumber(creditCardRequest.getCardNumber());
	        creditCard.setLimit(creditCardRequest.getLimit() );
	        creditCard.setName( creditCardRequest.getName() );
	        creditCard.setBalance(BigDecimal.valueOf(0));

	        return creditCard;
	}

   
	public  CreditCardResponse creditCardToCreditCardResponse(CreditCardData creditCard) {
		if ( creditCard == null ) {
            return null;
        }

		CreditCardResponse creditCardResponse = new CreditCardResponse();

        creditCardResponse.setLimit( CreditCardMapper.addCurrency( creditCard.getLimit() ) );
        creditCardResponse.setBalance( CreditCardMapper.addCurrency( creditCard.getBalance() ) );
        creditCardResponse.setCardNumber( creditCard.getCardNumber() );
        
        creditCardResponse.setName( creditCard.getName() );

        return creditCardResponse;
    }

    @Named("addCurrency")
        public static String addCurrency(final BigDecimal amt) {
        return amt + ServerConstants.DEFAULT_CURRENCY;
    }
}
