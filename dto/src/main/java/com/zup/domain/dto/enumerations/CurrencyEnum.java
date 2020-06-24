package com.zup.domain.dto.enumerations;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum CurrencyEnum {
  USD("USD"),
  EUR("EUR"),
  BRL("BRL");

  private String value;

  CurrencyEnum(String enumName) {
    this.value = enumName;
  }
}
