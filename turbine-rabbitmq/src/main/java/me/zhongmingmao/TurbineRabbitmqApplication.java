package me.zhongmingmao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

@SpringBootApplication
@EnableTurbineStream
public class TurbineRabbitmqApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(TurbineRabbitmqApplication.class, args);
    }
}
