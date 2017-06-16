package me.zhongmingmao.web;

import me.zhongmingmao.domain.User;
import me.zhongmingmao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
    
    @PostMapping("/post")
    public User post(@RequestBody User user) {
        return userRepository.save(user);
    }
}
