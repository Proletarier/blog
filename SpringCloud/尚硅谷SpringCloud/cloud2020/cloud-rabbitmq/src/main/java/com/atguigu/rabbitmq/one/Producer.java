package com.atguigu.rabbitmq.one;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    public  static  final  String  QUEUE_NAME ="hello";

    public  static  void  main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();

        factory.setHost("192.168.43.11");
        factory.setUsername("admin");
        factory.setPassword("123");

        final Connection connection = factory.newConnection();
        //获取信道
        Channel channel =connection.createChannel();
        /**
         * 生成一个队列
         * 1. 队列名称
         * 2. 队列里面的消息是否持久化（磁盘） 默认情况消息存在内存中
         * 3. 该队列是否只供一个消费者进行消费，是否进行消息共享
         */
        channel.queueDeclare(QUEUE_NAME,false,false, false,null);
        // 发消息
        String  message="hello world";
        /**
         * 1. 发送到那个交换价
         * 2. 路由的key值是那个 本次是队列的名称
         * 3. 其它参数信息
         * 4. 发送消息的消息体
         */
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("消息发送完毕");
    }
}
