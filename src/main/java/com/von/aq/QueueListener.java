package com.von.aq;

import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author ： fjl
 * @date ： 2019/8/19/019 9:53
 */
@Component
public class QueueListener {

//    @JmsListener(destination = "order", containerFactory = "jmsListenerContainerQueue")
//    @SendTo("out.queue")
//    public String receive(String text){
//        System.out.println("QueueListener: consumer-a 收到一条信息: " + text);
//        return "consumer-a received : " + text;
//    }
}
