package com.creditcard.system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("CREDIT_CARD_DATA")
public class CreditCardData 
{	
	 @Id
	 @Column("ID")
	 private Long id;
	@Column("CARD_NUMBER")
	private String cardNumber;
	@Column("CUST_NAME")
    private String name;
	@Column("BALANCE")
    private String balance;
	@Column("CREDIT_LIMIT")
    private String creditLimit;

   
   	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	
   
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

   


}
