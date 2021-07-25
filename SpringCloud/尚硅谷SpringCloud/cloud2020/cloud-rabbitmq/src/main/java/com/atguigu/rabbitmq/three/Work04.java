package com.atguigu.rabbitmq.three;

import com.atguigu.rabbitmq.one.utils.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.util.concurrent.TimeUnit;


public class Work04 {

    public  static  final  String   QUEUE_NAME="ack_queue";

    public static void main(String[] args) throws Exception {
        Channel channel= RabbitMqUtils.getChannel();

        /**
         * 声明接收消息
         */
        DeliverCallback deliverCallback= (consumerTag, message) ->{
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new String(message.getBody()));
            channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
        };

        /**
         * 取消消息的回调
         */

        CancelCallback cancelCallback= consumerTag->{
            System.out.println("消息消费被中断");
        };
        channel.basicQos(1);
        System.out.println("c3等待接收");
        channel.basicConsume(QUEUE_NAME,false,deliverCallback,cancelCallback);

    }

}
