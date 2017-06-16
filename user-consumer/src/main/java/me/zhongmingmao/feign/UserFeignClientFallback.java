package me.zhongmingmao.feign;

import me.zhongmingmao.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallback implements UserFeignClient {
    
    @Override
    public String findById(Long id) {
        return String.format("findById fallback,id=%s", id);
    }
    
    @Override
    public User post(User user) {
        return new User(-1L, "none");
    }
}
