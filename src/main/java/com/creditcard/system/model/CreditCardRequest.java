package com.creditcard.system.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.creditcard.system.utils.AllNumeric;
import com.creditcard.system.utils.LuhnValidation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class CreditCardRequest {
    @NotEmpty
    private String name;

    @NotNull(message = "Card number cannot be null")
    @Size(min = 13, message = "Card Number should not be less then 16")
    @AllNumeric(message = "Credit card number has to contain all numeric value")
    @LuhnValidation(message = "Credit card should be in valid format")
    private String cardNumber;

    @NotNull(message = "Card limit cannot be null")
    private BigDecimal limit;
}
