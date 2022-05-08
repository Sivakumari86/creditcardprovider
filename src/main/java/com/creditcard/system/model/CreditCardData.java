package com.creditcard.system.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
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
    private BigDecimal balance;
	@Column("CREDIT_LIMIT")
    private BigDecimal limit;

   
}
