package com.zup.rest.domain.exception.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlreadySavedException extends Exception {

  public AlreadySavedException() {
    super("PAYMENT_ALREADY_SAVED");
  }
}
