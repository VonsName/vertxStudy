package com.von.aq.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author ： fjl
 * @date ： 2019/11/7/007 9:53
 */
public class Application {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create();
        ActorRef actorRef = actorSystem.actorOf(Props.create(SendActor.class), "sender");
        actorRef.tell(999,actorRef);
        try {
            Thread.sleep(1000L);
            actorSystem.terminate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
