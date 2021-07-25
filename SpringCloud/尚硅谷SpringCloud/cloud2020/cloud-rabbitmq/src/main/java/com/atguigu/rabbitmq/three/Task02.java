package com.atguigu.rabbitmq.three;

import com.atguigu.rabbitmq.one.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.util.Scanner;

public class Task02 {

    public  static  final  String   QUEUE_NAME="ack_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        channel.queueDeclare(QUEUE_NAME,true,false, false,null);

        Scanner scanner=new Scanner(System.in);

        while (scanner.hasNext()){
            String message=scanner.next();
            channel.basicPublish("",QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());

            System.out.println("发送消息成功...."+message);
        }
    }


}
