package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("payment")
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private  String serverPort;

    @GetMapping("/hystrix/ok/{id}")
    public String getPaymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentService.getPaymentInfo_OK(id);
        log.info(result);
        return  result;
    }


    @GetMapping("/hystrix/timeout/{id}")
    public String getPaymentInfo_Timeout(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_Timeout(id);
        log.info(result);
        return  result;
    }


    @GetMapping("info/error/{id}")
    public String getPaymentInfo_Error(@PathVariable("id") Integer id){
        String res = paymentService.getPaymentInfo_Error(id);
        return res;
    }

    @GetMapping("info/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String res = paymentService.paymentCircuitBreaker(id);
        return res;
    }

}
