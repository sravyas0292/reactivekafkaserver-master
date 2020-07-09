package com.flowdim.demo.reactivekafkawebsocket;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.internals.Sender;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;
import reactor.kafka.sender.SenderOptions;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

@Service
public class KafkaServiceImpl implements KafkaService {
    private static final String BOOTSTRAP_SERVERS = "pkc-lgwgm.eastus2.azure.confluent.cloud:9092";

    private static final String BOOTSTRAP_SERVERS_LOCAL = "localhost:9092";


    private Flux<ReceiverRecord<String, String>> testTopicStream;


    private Flux<ProducerRecord<String, String>> producerTopicStream;


    KafkaServiceImpl() throws IOException {

//        Properties kafkaProperties = PropertiesLoaderUtils.loadAllProperties("ccloud.properties");
//        kafkaProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
//        kafkaProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, "reactive-consumer");
//        kafkaProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "sample-group");
//        kafkaProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        kafkaProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        kafkaProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG,"reactive-consumer");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "sample-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        ReceiverOptions<String, String> receiverOptions = ReceiverOptions.create(props);

        testTopicStream = createTopicCache(receiverOptions, "LineChartTopic");
    }


    public Flux<ReceiverRecord<String, String>> getTestTopicFlux() {

        return testTopicStream;
    }

    private <T, G> Flux<ReceiverRecord<T, G>> createTopicCache(ReceiverOptions<T, G> receiverOptions, String topicName) {
        ReceiverOptions<T, G> options = receiverOptions.subscription(Collections.singleton(topicName));

        return KafkaReceiver.create(options).receive().cache();
    }

}
