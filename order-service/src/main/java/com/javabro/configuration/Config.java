package com.javabro.configuration;

import com.javabro.events.OrderCreatedEvent;
import com.javabro.events.OrderPaymentEvent;
import com.javabro.events.OrderShipmentEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Config {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<Long, OrderCreatedEvent> orderCreatedEventProducerFactory() {
        return new DefaultKafkaProducerFactory(producerConfigs());
    }
    @Bean
    public KafkaTemplate<Long, OrderCreatedEvent> getOrderCreatedEventTemplate() {
        return new KafkaTemplate(orderCreatedEventProducerFactory());
    }

    @Bean
    public ProducerFactory<Long, OrderPaymentEvent> orderPaymentEventProducerFactory() {
        return new DefaultKafkaProducerFactory(producerConfigs());
    }
    @Bean
    public KafkaTemplate<Long, OrderPaymentEvent> getOrderPaymentEventTemplate() {
        return new KafkaTemplate(orderPaymentEventProducerFactory());
    }

    @Bean
    public ProducerFactory<Long, OrderShipmentEvent> orderShipmentEventProducerFactory() {
        return new DefaultKafkaProducerFactory(producerConfigs());
    }
    @Bean
    public KafkaTemplate<Long, OrderShipmentEvent> getOrderShipmentEventTemplate() {
        return new KafkaTemplate(orderShipmentEventProducerFactory());
    }

}
