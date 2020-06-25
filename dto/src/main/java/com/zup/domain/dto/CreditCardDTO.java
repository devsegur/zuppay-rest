package com.zup.domain.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CreditCardDTO extends BaseDTO {

  private UUID creditCardId;

  private String ownerName;

  private String cardNumber;

  private String expirationDate;

  private String securityCode;

  private PaymentDTO payment;

  private TransactionDTO transaction;
}