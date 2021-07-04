package com.atguigu.springcloud;

import com.atguigu.lb.MyLB;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
public class AtomicIntegerTest {


    @Test
    public void  test1() throws InterruptedException {
        MyLB myLB=new MyLB();
        while (true){
            myLB.getAndIncrement();
            TimeUnit.SECONDS.sleep(1);
        }
    }


    @Test
    public void  test() throws InterruptedException {
        AtomicInteger atomicInteger=new AtomicInteger(0);

        do {
            System.out.println(atomicInteger.incrementAndGet());
            TimeUnit.SECONDS.sleep(1);
           System.out.println(atomicInteger.compareAndSet(3,5000));
        }while (true);
    }

}
