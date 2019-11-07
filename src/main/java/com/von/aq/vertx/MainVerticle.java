package com.von.aq.vertx;

import io.vertx.core.AbstractVerticle;

/**
 * @author ： fjl
 * @date ： 2019/11/7/007 10:38
 */
public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.deployVerticle(MVertx.class.getName());
    }
}
