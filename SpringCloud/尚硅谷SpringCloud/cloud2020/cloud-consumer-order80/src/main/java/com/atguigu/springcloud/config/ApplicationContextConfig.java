package com.atguigu.springcloud.config;

import com.atguigu.lb.LoadBalancer;
import com.atguigu.lb.MyLB;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    public RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }

    @Bean
    public LoadBalancer getLoadBalancer(){
        return  new MyLB();
    }

}
