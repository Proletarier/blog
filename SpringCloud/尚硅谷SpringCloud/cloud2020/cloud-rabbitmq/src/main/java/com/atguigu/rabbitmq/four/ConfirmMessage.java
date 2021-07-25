package com.atguigu.rabbitmq.four;

import com.atguigu.rabbitmq.one.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;

import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;


public class ConfirmMessage {

    public  static  final  int   MESSAGE_COUNT = 1000;

    public static void main(String[] args) throws Exception {
        publishMessageAsync();
    }


    public  static  void  publishMessageBatch() throws Exception {
        Channel channel=  RabbitMqUtils.getChannel();
        //队列的声明
        String queueName= UUID.randomUUID().toString();
        channel.queueDeclare(queueName,true,false,false,null);

        //开启发布确认
        channel.confirmSelect();
        //开始时间
        long begin=System.currentTimeMillis();
        // 批量发消息
        for (int i=0; i<MESSAGE_COUNT ; i ++){
            String message = 1 +"";
            channel.basicPublish("",queueName,null,message.getBytes());
            boolean flag = channel.waitForConfirms();

        }

        long end=System.currentTimeMillis();
        System.out.println(end-begin);
    }

    public  static  void  publishMessageIndividually() throws Exception {
        Channel channel=  RabbitMqUtils.getChannel();
        //队列的声明
        String queueName= UUID.randomUUID().toString();
        channel.queueDeclare(queueName,true,false,false,null);

        //开启发布确认
        channel.confirmSelect();
        //开始时间
        long begin=System.currentTimeMillis();
        // 批量发消息
        for (int i=0; i<MESSAGE_COUNT ; i ++){
             String message = 1 +"";
             channel.basicPublish("",queueName,null,message.getBytes());
             boolean flag = channel.waitForConfirms();
             if(flag)
                  System.out.println("消息发送成功");
        }

        long end=System.currentTimeMillis();
        System.out.println(end-begin);
    }

    //异步发布确认
    public  static  void  publishMessageAsync() throws Exception {
        Channel channel=  RabbitMqUtils.getChannel();
        //队列的声明
        String queueName= UUID.randomUUID().toString();
        channel.queueDeclare(queueName,true,false,false,null);

        //开启发布确认
        channel.confirmSelect();

        ConcurrentSkipListMap<Long,String> concurrentSkipListMap= new ConcurrentSkipListMap<>();



        //开始时间
        long begin=System.currentTimeMillis();

        //发送成功监听器
        ConfirmCallback ackCallback=(deliverTag,multiple) ->{

            if(multiple){
                ConcurrentNavigableMap<Long, String> headMap = concurrentSkipListMap.headMap(deliverTag);
                headMap.clear();
            }else {
                concurrentSkipListMap.remove(deliverTag);
            }

            System.out.println("确认的消息"+ deliverTag);
        };
        //发送失败监听器
        ConfirmCallback nackCallback=(deliverTag,multiple) ->{
            String  message = concurrentSkipListMap.get(deliverTag);
            System.out.println("未确认的消息"+ deliverTag+":  "+message);

        };

        //消息监听器
        channel.addConfirmListener(ackCallback,nackCallback);

        // 批量发消息
        for (int i=0; i<MESSAGE_COUNT ; i ++){
            String message = 1 +"";
            channel.basicPublish("",queueName,null,message.getBytes());
            concurrentSkipListMap.put(channel.getNextPublishSeqNo(),message);
        }

        long end=System.currentTimeMillis();
        System.out.println(end-begin);
    }
}
