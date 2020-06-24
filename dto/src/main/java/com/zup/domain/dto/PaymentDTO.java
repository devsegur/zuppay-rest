package com.zup.domain.dto;

import com.zup.domain.dto.enumerations.CurrencyEnum;
import java.math.BigDecimal;
import java.time.LocalDate;
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
public class PaymentDTO extends BaseDTO {
  private UUID paymentId;

  private String productId;

  private String description;

  private LocalDate dueDate;

  private BigDecimal money;

  private CurrencyEnum currency;

  private CreditCardDTO creditCard;

  private TransactionDTO transaction;
}
