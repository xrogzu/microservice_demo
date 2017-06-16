package me.zhongmingmao.feign;

import config.feign.UserProviderFeignConfig;
import me.zhongmingmao.domain.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// FeignClient用于创建一个 Ribbon 负载均衡器
// 当使用Eureka时,Ribbon会把name解析成Eureka Server上的服务
// 否则解析成service.ribbon.listOfServers
@FeignClient(name = "user-provider", configuration = UserProviderFeignConfig.class)
public interface UserFeignClient {
    
    @GetMapping("/users/{id}")
    String findById(@PathVariable("id") Long id);
    
    @PostMapping("/users/post")
    User post(@RequestBody User user);
}