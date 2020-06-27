package com.zup.rest.domain.exception.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundedException extends Exception {

  public NotFoundedException() {
    super("NOT_FOUNDED");
  }
}
