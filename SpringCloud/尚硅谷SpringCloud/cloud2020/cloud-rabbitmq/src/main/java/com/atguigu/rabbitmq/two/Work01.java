package com.atguigu.rabbitmq.two;

import com.atguigu.rabbitmq.one.utils.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;


public class Work01 {

    public  static  final  String   QUEUE_NAME="hello";

    public static void main(String[] args) throws Exception {
        Channel channel= RabbitMqUtils.getChannel();

        /**
         * 声明接收消息
         */
        DeliverCallback deliverCallback= (consumerTag, message) ->{
            System.out.println(new String(message.getBody()));
        };

        /**
         * 取消消息的回调
         */

        CancelCallback cancelCallback= consumerTag->{
            System.out.println("消息消费被中断");
        };

        System.out.println("c3等待接收");
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);

    }

}
