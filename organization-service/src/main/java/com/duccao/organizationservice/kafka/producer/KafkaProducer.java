//package com.duccao.organizationservice.kafka.producer;
//
//import com.duccao.organizationservice.configs.KafkaConfigProperties;
//import com.duccao.organizationservice.kafka.KafkaTopic;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.KafkaTemplate;
//
///**
// * Description of the class goes here.
// *
// * @author Duc Cao
// * @version 1.0
// * @since 9/5/2024
// */
//
//@Configuration
//public class KafkaProducer {
//
//  @Bean
//  public KafkaTemplate<String, String> stringTemplate(KafkaConfigProperties properties) {
//    KafkaTemplateFactory<String> factory = new KafkaTemplateFactory<>();
//    return factory.kafkaTemplate(properties, KafkaTopic.EXAMPLE_TOPIC.getName());
//  }
//}
