package com.duccao.gatewayserver.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Description of the class goes here.
 *
 * @author Duc Cao
 * @version 1.0
 * @since 9/4/2024
 */
@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    http.authorizeExchange(auth -> auth.anyExchange().authenticated())
      .oauth2Login(Customizer.withDefaults())
      .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
    http.csrf(ServerHttpSecurity.CsrfSpec::disable);
    return http.build();
  }
}
