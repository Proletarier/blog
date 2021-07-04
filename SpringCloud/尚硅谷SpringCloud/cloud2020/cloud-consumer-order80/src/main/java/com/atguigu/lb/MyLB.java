package com.atguigu.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class MyLB implements  LoadBalancer {

    private AtomicInteger atomicInteger=new AtomicInteger(0);


    public final int getAndIncrement(){
        int current;
        int next;
        boolean isOK;
        do {
            current = atomicInteger.get();
            next = current >= Integer.MAX_VALUE? 0 : current + 1;
            System.out.println("第几次循环，次数next:"+ next +"current:"+current);
            isOK=atomicInteger.compareAndSet(current,next);
            System.out.println(isOK);
        }while (!isOK);
        System.out.println("第几次访问，次数next:"+ next);
        return next;
    }


    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
       int index = getAndIncrement() % serviceInstances.size();
       return  serviceInstances.get(index);
    }
}
