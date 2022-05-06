package com.creditcard.system.utils;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.creditcard.system.model.CreditCardData;

public class CreditCardValidator implements ConstraintValidator<LuhnValidation, String> {
	    @Override
	    public boolean isValid(final String cardNumber, final ConstraintValidatorContext constraintValidatorContext) {
	       boolean value= luhnCheck(cardNumber);
	       return value;
	    }
	    
	    /**
	     * This is the validator to verify is a card valid based on Luhn's algorithm
	     *
	     * @param cardNumber cardNumber to be validated
	     * @return is valid or not
	     */    
	public static boolean luhnCheck(String cardNumber) {
		  int[] cardIntArray = new int[cardNumber.length()];

	        for (int i = 0; i < cardNumber.length(); i++) {
	             char c = cardNumber.charAt(i);
	            if (!Character.isDigit(c)) {
	                return false;
	            }
	            cardIntArray[i] = Integer.parseInt("" + c);
	        }

	        for (int i = cardIntArray.length - 2; i >= 0; i = i - 2) {
	            int num = cardIntArray[i];
	            num = num * 2;
	            if (num > 9) {
	                num = num % 10 + num / 10;
	            }
	            cardIntArray[i] = num;
	        }

	        return Arrays.stream(cardIntArray).sum() % 10 == 0;

	    }
	
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
