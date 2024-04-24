package com.example.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @KafkaListener(topics = "test", groupId = "group_id")
    public void consume(String employee) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        var emp = objectMapper.readValue(employee, Employee.class);
        System.out.println("Employee Details: " + emp);
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Employee {
    private String name;
    private String dept;
    private Long salary;
}
