package com.creditcard.system.event;

import org.springframework.context.ApplicationEvent;

import com.creditcard.system.model.CreditCardData;
/**
 * Event thrown when Card is added 
 */
public class CardEvent extends ApplicationEvent {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static final String CARD_CREATED = "CREATED";
	    

	    private String eventType;

	    public CardEvent(String eventType, CreditCardData creditCardData) {
	        super(creditCardData);
	        this.eventType = eventType;
	    }

	    public String getEventType() {
	        return eventType;
	    }
}
