package com.duccao.organizationservice.kafka.topics;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description of the class goes here.
 *
 * @author Duc Cao
 * @version 1.0
 * @since 9/5/2024
 */
@Configuration
public class TopicConfigurations {

  private static final short REPLICATION_FACTOR = 1;
  private static final int NUMBER_OF_PARTITIONS = 3;

  @Bean(name = "example-topic")
  public RequestTopic getExampleRequestTopic() {
    return () -> "example-topic";
  }

  @Bean
  public NewTopic getExampleTopic(@Qualifier("example-topic") RequestTopic topic) {
    Map<String, String> configs = new HashMap<>();
    configs.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
    return new NewTopic(topic.getName(), NUMBER_OF_PARTITIONS, REPLICATION_FACTOR).configs(configs);
  }
}
