package me.zhongmingmao.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/instances")
public class InstanceController {
    
    private static final String USER_SERVICE_ID = "user-provider";
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    
    @Autowired
    private DiscoveryClient discoveryClient;
    
    @GetMapping("/user-provider-list")
    public List<ServiceInstance> userProviders() {
        return discoveryClient.getInstances(USER_SERVICE_ID);
    }
    
    @GetMapping("/current-user-provider")
    public String currentUserProvider() {
        ServiceInstance instance = loadBalancerClient.choose(USER_SERVICE_ID);
        return String.format("%s@%s:%s", instance.getServiceId(), instance.getHost(), instance.getPort());
    }
}