package com.example.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Bean
    public NewTopic topic() {
        return new NewTopic("test", 1, (short) 1);
    }

}

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
class KafkaProducer {

    private final KafkaTemplate<String, Employee> kafkaTemplate;

    @PostMapping("/send")
    public String sendMessage(@RequestBody Employee employee) throws JsonProcessingException {
        kafkaTemplate.send("test", employee);
        return "Message sent successfully";
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Employee{

    private String name;
    private String dept;
    private Long salary;
}