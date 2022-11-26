package br.com.brunaenaira.springpattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"br.com.brunaenaira.springpattern.service"})
public class SpringPatternApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPatternApplication.class, args);
    }
}
