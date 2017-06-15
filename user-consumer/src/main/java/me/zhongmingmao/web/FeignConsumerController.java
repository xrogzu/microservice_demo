package me.zhongmingmao.web;

import me.zhongmingmao.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign/users")
public class FeignConsumerController {
    
    @Autowired
    private UserFeignClient userFeignClient;
    
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id) {
        return userFeignClient.findById(id);
    }
}
