package com.duccao.gatewayserver.filters;

import com.duccao.gatewayserver.utils.FilterUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

/**
 * Description of the class goes here.
 *
 * @author Duc Cao
 * @version 1.0
 * @since 9/4/2024
 */

@Configuration
@Slf4j
@RequiredArgsConstructor
public class ResponseFilter {

  private final FilterUtils filterUtils;

  @Bean
  public GlobalFilter postFilter() {
    return (exchange, chain) -> chain.filter(exchange).then(Mono.fromRunnable(() -> {
      HttpHeaders headers = exchange.getRequest().getHeaders();
      String correlationId = filterUtils.getCorrelationId(headers);
      log.info("Adding the correlation id to the outbound headers. {}",
        correlationId);

      exchange.getResponse().getHeaders().add(FilterUtils.CORRELATION_ID, correlationId);
      log.info("Completing outgoing request for {}.", exchange.getRequest().getURI());
    }));
  }
}
