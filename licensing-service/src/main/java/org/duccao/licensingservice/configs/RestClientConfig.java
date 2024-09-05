package org.duccao.licensingservice.configs;

import org.duccao.licensingservice.utils.UserContext;
import org.duccao.licensingservice.utils.UserContextHolder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * Description of the class goes here.
 *
 * @author Duc Cao
 * @version 1.0
 * @since 9/4/2024
 */
@Configuration
public class RestClientConfig {

  @LoadBalanced
  @Bean
  RestClient.Builder restClientBuilder() {
    return RestClient.builder();
  }
}
