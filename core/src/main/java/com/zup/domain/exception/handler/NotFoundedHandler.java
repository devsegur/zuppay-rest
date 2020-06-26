package com.zup.domain.exception.handler;

import com.zup.domain.exception.message.NotFoundedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class NotFoundedHandler extends ResponseEntityExceptionHandler {

  private NotFoundedException message;
}
