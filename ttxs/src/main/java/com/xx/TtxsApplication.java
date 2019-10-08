package com.xx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class TtxsApplication {
    public static void main(String[] args) {
        SpringApplication.run(TtxsApplication.class,args);
    }
}
