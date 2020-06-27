package com.zup.rest.domain.exception.handler;

import com.zup.rest.domain.exception.message.NotFoundedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class NotFoundedHandler extends ResponseEntityExceptionHandler {

  private NotFoundedException message;
}
