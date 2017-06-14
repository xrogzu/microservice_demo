package me.zhongmingmao.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/users")
public class UserConsumerController {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id) {
        return restTemplate.getForObject(String.format("http://USER-PROVIDER/users/%s", id), String.class);
    }
}