package com.asan.couirertracking.configuration;

import com.asan.couirertracking.dto.requestdto.CourierStoreSaveRequestDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;
@EnableKafka
@Configuration
public class KafkaConfiguration {

	@Value(value = "${kafka.bootstrap-servers}")
	private String bootstrapAddress;

	@Value("${kafka.acks:all}")
	protected String acks;
	@Bean
	public ProducerFactory<String, Object> producerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(
				ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				bootstrapAddress);
		configProps.put(
				ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				StringSerializer.class);
		configProps.put(
				ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				StringSerializer.class);
		configProps.put(ProducerConfig.ACKS_CONFIG, acks);

		return new DefaultKafkaProducerFactory<>(configProps);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, CourierStoreSaveRequestDto> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, CourierStoreSaveRequestDto> factory
				= new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.setBatchListener(true);
		return factory;
	}

	@Bean
	public ConsumerFactory<String, CourierStoreSaveRequestDto> consumerFactory() {
		JsonDeserializer<CourierStoreSaveRequestDto> deserializer = new JsonDeserializer<>(CourierStoreSaveRequestDto.class);

		Map<String, Object> props = new HashMap<>();
		props.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, "courier-store-id");
		props.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				StringDeserializer.class);
		props.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				deserializer);
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
	}


	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
//
////
//	@Bean
//	public NewTopic compactTopicExample() {
//		return TopicBuilder.name("courierTopic")
//				.partitions(1)
//				.replicas(1)
//				.compact()
//				.build();
//	}

//	@Bean
//	public KafkaAdmin kafkaAdmin() {
//		Map<String, Object> configs = new HashMap<>();
//		// Depending on you Kafka Cluster setup you need to configure
//		// additional properties!
//		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//		return new KafkaAdmin(configs);
//	}
}
