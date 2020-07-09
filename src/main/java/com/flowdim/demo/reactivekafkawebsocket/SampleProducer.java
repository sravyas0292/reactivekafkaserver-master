package com.flowdim.demo.reactivekafkawebsocket;

import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowdim.demo.reactivekafkawebsocket.DTO.AgeDTO;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;


public class SampleProducer {
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";

    private Producer<String, String> producer;

    public SampleProducer() throws IOException {

        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "sample-producer");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producer = new KafkaProducer<String, String>(props);
    }

    public void sendMessage(String topic, List<AgeDTO> count) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String type = mapper.writeValueAsString(count);
        ProducerRecord record = new ProducerRecord(topic, "1", type);
        producer.send(record);
        producer.flush();
        producer.close();
    }
}

