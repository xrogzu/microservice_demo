package me.zhongmingmao.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope // post /refresh 获取配置中心最新的值
@RestController
public class ConfigController {
    
    @Value("${version}")
    private String version;
    
    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public String version() {
        return version;
    }
}
