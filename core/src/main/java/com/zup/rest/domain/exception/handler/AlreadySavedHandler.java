package com.zup.rest.domain.exception.handler;

import com.zup.rest.domain.exception.message.AlreadySavedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class AlreadySavedHandler extends ResponseEntityExceptionHandler {

  private AlreadySavedException message;
}
