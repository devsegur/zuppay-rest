package com.zup.domain.entity;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CreditCard {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NotNull
  private UUID creditCardId;

  @Max(value = 100)
  @NotNull
  private String ownerName;

  @Max(value = 100)
  @NotNull
  private String cardNumber;

  @Max(value = 100)
  @NotNull
  private String expirationDate;

  @Max(value = 100)
  @NotNull
  private String securityCode;
}
