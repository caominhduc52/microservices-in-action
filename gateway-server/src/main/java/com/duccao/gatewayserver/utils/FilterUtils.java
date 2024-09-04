package com.duccao.gatewayserver.utils;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * Description of the class goes here.
 *
 * @author Duc Cao
 * @version 1.0
 * @since 9/4/2024
 */
@Component
public class FilterUtils {

  public static final String CORRELATION_ID = "x-correlation-id";

  public String getCorrelationId(HttpHeaders requestHeaders) {
    if (requestHeaders.get(CORRELATION_ID) != null) {
      List<String> header = requestHeaders.get(CORRELATION_ID);
      return header.stream().findFirst().get();
    }
    return null;
  }

  public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
    return exchange.mutate()
      .request(
        exchange.getRequest().mutate()
          .header(CORRELATION_ID, correlationId)
          .build()
      )
      .build();
  }
}
