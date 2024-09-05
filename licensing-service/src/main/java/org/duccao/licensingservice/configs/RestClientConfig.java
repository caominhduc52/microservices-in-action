package org.duccao.licensingservice.configs;

import org.duccao.licensingservice.utils.UserContext;
import org.duccao.licensingservice.utils.UserContextHolder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.client.RestClientBuilderConfigurer;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
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

  @Bean
  @LoadBalanced
  @Primary
  RestClient.Builder restClientBuilder(RestClientBuilderConfigurer restClientBuilderConfigurer) {
    RestClient.Builder builder = RestClient.builder().requestFactory(
      ClientHttpRequestFactories.get(ClientHttpRequestFactorySettings.DEFAULTS));
    return restClientBuilderConfigurer.configure(builder);
  }
}
