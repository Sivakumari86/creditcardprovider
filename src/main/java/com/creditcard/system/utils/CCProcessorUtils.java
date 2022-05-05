package com.creditcard.system.utils;

import com.creditcard.system.model.CreditCardData;

public class CCProcessorUtils {
	
	 public static boolean validateCardDetails(CreditCardData creditCardData) {
	        boolean cardResult = true;

	        try {
	            if (creditCardData.getCardNumber().length() > 19 || Long.parseLong(creditCardData.getCardNumber()) < 0) {
	                cardResult = false;
	            }
	        } catch (NumberFormatException e) {
	            cardResult = false;
	        }

	        return cardResult;
	    }

	    
	   
}
