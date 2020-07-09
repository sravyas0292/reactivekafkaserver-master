package com.flowdim.demo.reactivekafkawebsocket;

import com.flowdim.demo.reactivekafkawebsocket.DTO.AgeDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;
@EnableScheduling
@SpringBootApplication
public class ReactiveKafkaWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveKafkaWebsocketApplication.class, args);
    }

}
