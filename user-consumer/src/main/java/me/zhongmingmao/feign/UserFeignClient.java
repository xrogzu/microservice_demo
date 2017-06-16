package me.zhongmingmao.feign;

import config.feign.UserProviderFeignConfig;
import me.zhongmingmao.domain.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// FeignClient用于创建一个Ribbon负载均衡器
// 当使用Eureka时,Ribbon会把name解析成Eureka Server上的服务
// 否则解析成service.ribbon.listOfServers
@FeignClient(
        name = "user-provider",
        configuration = UserProviderFeignConfig.class, // 自定义配置，不应该在UserConsumerApplication的扫描范围内
//        fallback = UserFeignClientFallback.class, // Feign已经整合了Hystrix，简单回退
        fallbackFactory = UserFeignClientFallbackFactory.class) // 能获知回退的原因，优先级低于fallback
public interface UserFeignClient {
    
    @GetMapping("/users/{id}")
    String findById(@PathVariable("id") Long id);
    
    @PostMapping("/users/post")
    User post(@RequestBody User user);
}