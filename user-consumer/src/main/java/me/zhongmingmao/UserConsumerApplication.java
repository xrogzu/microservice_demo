package me.zhongmingmao;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients
public class UserConsumerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(UserConsumerApplication.class, args);
    }
}
