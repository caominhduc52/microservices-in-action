//package com.duccao.organizationservice.kafka.producer;
//
//import com.duccao.organizationservice.configs.KafkaConfigProperties;
//import java.util.HashMap;
//import java.util.Map;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
///**
// * Description of the class goes here.
// *
// * @author Duc Cao
// * @version 1.0
// * @since 9/5/2024
// */
//public class KafkaTemplateFactory<T> {
//
//  private ProducerFactory<String, T> producerFactory(KafkaConfigProperties properties) {
//    return new DefaultKafkaProducerFactory<>(getConfigurations(properties));
//  }
//
//  public Map<String, Object> getConfigurations(KafkaConfigProperties properties) {
//    Map<String, Object> configs = new HashMap<>();
//    configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServer());
//    configs.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, properties.getMaxRequestSize());
//    configs.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, properties.getRequestTimeoutMs());
//    configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//    configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//    return configs;
//  }
//
//  public KafkaTemplate<String, T> kafkaTemplate(KafkaConfigProperties properties, String topic) {
//    KafkaTemplate<String, T> kafkaTemplate = new KafkaTemplate<>(producerFactory(properties));
//    kafkaTemplate.setDefaultTopic(topic);
//    return kafkaTemplate;
//  }
//}
