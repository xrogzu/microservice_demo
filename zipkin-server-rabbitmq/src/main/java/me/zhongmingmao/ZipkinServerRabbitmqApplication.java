package me.zhongmingmao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

@SpringBootApplication
@EnableZipkinStreamServer
public class ZipkinServerRabbitmqApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerRabbitmqApplication.class, args);
    }
}