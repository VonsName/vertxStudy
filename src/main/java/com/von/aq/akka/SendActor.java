package com.von.aq.akka;

import akka.actor.AbstractActor;

/**
 * @author ： fjl
 * @date ： 2019/11/7/007 9:49
 */
public class SendActor extends AbstractActor {
    @Override
    public Receive createReceive() {

        return receiveBuilder().
                match(String.class,msg-> System.out.println("i receive msg:"+msg)).
                matchAny(msg-> System.out.println("who are you"+msg)).
                build();
    }
}
