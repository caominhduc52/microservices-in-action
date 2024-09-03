package org.duccao.licensingservice.errorhandling;

/**
 * Description of the class goes here.
 *
 * @author Duc Cao
 * @version 1.0
 * @since 9/3/2024
 */

import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler(value = CallNotPermittedException.class)
  public ResponseEntity<?> handleCallNotPermittedException(CallNotPermittedException e) {
    return ResponseEntity.unprocessableEntity().body(e.toString());
  }

  @ExceptionHandler(value = BulkheadFullException.class)
  public ResponseEntity<?> handleBulkheadFullException(BulkheadFullException e) {
    return ResponseEntity.unprocessableEntity().body(e.toString());
  }
}
