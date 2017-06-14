package me.zhongmingmao.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("instances")
public class InstanceController {
    
    @Autowired
    private DiscoveryClient discoveryClient;
    
    @GetMapping("/user-provider")
    public List<ServiceInstance> userProviders() {
        return discoveryClient.getInstances("user-provider");
    }
}