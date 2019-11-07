package com.von.aq.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.ext.web.Router;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ： fjl
 * @date ： 2019/11/7/007 15:54
 */
public class MVertxWebSocket extends AbstractVerticle {

    private static final Map<String, ServerWebSocket> map = new ConcurrentHashMap<>(16);

    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route("/ws").handler(routingContext -> routingContext.response().sendFile("D:\\myTest\\von-activemq\\src\\main\\java\\com\\von\\aq\\vertx\\so.html"));

        server.websocketHandler(serverWebSocket -> {
            String s = serverWebSocket.binaryHandlerID();
            map.putIfAbsent(s, serverWebSocket);
            serverWebSocket.frameHandler(handler -> {
                String data = handler.textData();
                String cuID = serverWebSocket.binaryHandlerID();
                for (Map.Entry<String, ServerWebSocket> entry : map.entrySet()) {
                    String key = entry.getKey();
                    if (!key.equalsIgnoreCase(cuID)) {
                        ServerWebSocket socket = entry.getValue();
                        socket.writeTextMessage("\r\n"+data + "\r\n");
                    }
                }
            });

            serverWebSocket.closeHandler(handler -> map.remove(s));
        });

        server.requestHandler(router::accept).listen(8080);
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(MVertxWebSocket.class.getName());
    }
}
