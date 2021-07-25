package com.atguigu.springcloud.service.impl;

import cn.hutool.core.lang.UUID;
import com.atguigu.springcloud.service.MessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;


@EnableBinding(Source.class)
public class MessageProviderImpl  implements MessageProvider {

    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("**** serial:" + serial);
        return null;
    }
}
