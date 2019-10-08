package com.xx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WyxxApplication {
    public static void main(String[] args) {
        SpringApplication.run(WyxxApplication.class,args);
    }
}
