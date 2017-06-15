package me.zhongmingmao.web;

import me.zhongmingmao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Value("#{environment['spring.cloud.client.hostname']}") // SPEL
    private String hostname;
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id) {
        return String.format("[%s response %s", hostname, userRepository.findOne(id));
    }
}
