package com.atguigu.springcloud.controller;

import com.atguigu.lb.LoadBalancer;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.xml.ws.Response;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("comsumer")
public class OrderController {

    public static  final  String PAYENT_URL="http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;
    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;


    @GetMapping("payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYENT_URL+"/payment/create",payment,CommonResult.class);
    }
    @GetMapping("payment/selectOne/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") long id) {
        return restTemplate.getForObject(PAYENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("payment/getForEntity/{id}")
    public ResponseEntity<?> getPayment2(@PathVariable("id") long id){
        return restTemplate.getForEntity(PAYENT_URL+"/payment/get/"+id,CommonResult.class);
//        if(entity.getStatusCode().is2xxSuccessful()){
//            return  entity.getBody();
//        } else {
//            return  new CommonResult<>(444,"error");
//
//        }
    }

    @GetMapping("payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() == 0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instance(instances);
        URI uri = serviceInstance.getUri();
        System.out.println(uri+"/payment/lb");
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
//        return uri+"/payment/lb";
    }




}
