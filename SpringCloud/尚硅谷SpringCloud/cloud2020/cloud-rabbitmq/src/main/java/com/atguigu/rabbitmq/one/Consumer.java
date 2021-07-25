package com.atguigu.rabbitmq.one;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {

    public  static  final  String   QUEUE_NAME="hello";

    public static void main(String[] args) throws Exception {
        // 创建连接工厂
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("192.168.43.11");
        factory.setUsername("admin");
        factory.setPassword("123");

        Connection connection = factory.newConnection();

        Channel channel =  connection.createChannel();


        /**
         * 声明接收消息
         */
        DeliverCallback deliverCallback= (consumerTag,message) ->{
            System.out.println(new String(message.getBody()));
        };

        /**
         * 取消消息的回调
         */

        CancelCallback cancelCallback= consumerTag->{
          System.out.println("消息消费被中断");
        };


        /**
         * 1. 消费那个队列
         * 2. 消费成功后是否要自动应答，true代表的自动应答，false代表手动应答
         * 3. 消费者未成功消费的回调
         * 4. 消费者录取消费者的回调
         */
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);


    }
}
