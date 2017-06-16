package me.zhongmingmao.web;

import me.zhongmingmao.domain.User;
import me.zhongmingmao.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feign/users")
public class FeignConsumerController {
    
    @Autowired
    private UserFeignClient userFeignClient;
    
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id) {
        return userFeignClient.findById(id);
    }
    
    @PostMapping(value = "/post")
    public User post(User user) {
        return userFeignClient.post(user);
    }
}