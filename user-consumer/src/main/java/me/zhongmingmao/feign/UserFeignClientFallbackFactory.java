package me.zhongmingmao.feign;

import feign.hystrix.FallbackFactory;
import me.zhongmingmao.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserFeignClientFallbackFactory.class);
    
    @Override
    public UserFeignClient create(Throwable cause) {
        return new UserFeignClient() {
            
            @Override
            public String findById(Long id) {
                return String.format("findById fallback,id:%s,reason:%s", id, cause.getMessage());
            }
            
            @Override
            public User post(User user) {
                UserFeignClientFallbackFactory.LOGGER.info("findById fallback reason", cause);
                return new User(-1L, "none");
            }
        };
    }
}
