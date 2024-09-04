package com.duccao.gatewayserver.filters;

import com.duccao.gatewayserver.utils.FilterUtils;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Description of the class goes here.
 *
 * @author Duc Cao
 * @version 1.0
 * @since 9/4/2024
 */

@Order(1)
@Component
@Slf4j
@RequiredArgsConstructor
public class TrackingFilter implements GlobalFilter {

  private final FilterUtils filterUtils;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    HttpHeaders headers = exchange.getRequest().getHeaders();
    if (isCorrelationIdPresent(headers)) {
      log.info("tmx-correlation-id found in tracking filter: {}. ",
        filterUtils.getCorrelationId(headers));
    } else {
      String correlationId = generateCorrelationId();
      exchange = filterUtils.setCorrelationId(exchange, correlationId);
      log.info(
        "tmx-correlation-id generated in tracking filter: {}.",
        correlationId);
    }
    return chain.filter(exchange);
  }

  private String generateCorrelationId() {
    return UUID.randomUUID().toString();
  }

  private boolean isCorrelationIdPresent(HttpHeaders headers) {
    if (filterUtils.getCorrelationId(headers) != null) {
      return true;
    }
    return false;
  }
}
