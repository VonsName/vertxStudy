package com.von.aq.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

/**
 * @author ： fjl
 * @date ： 2019/11/7/007 14:26
 */
public class MVertx01 extends AbstractVerticle {


    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);
//        router.route( HttpMethod.GET, "/some/path/").handler(routingContext -> {
//
//            HttpServerResponse response = routingContext.response();
//            // 由于我们会在不同的处理器里写入响应，因此需要启用分块传输
//            // 仅当需要通过多个处理器输出响应时才需要
//            response.setChunked(true);
//
//            response.write("route11111111\n");
//
//            // 5 秒后调用下一个处理器
////            routingContext.vertx().setTimer(1000, tid -> routingContext.next());
//            routingContext.next();
//        });


        router.route("/some/path/").handler(routingContext -> {

//            HttpServerResponse response = routingContext.response();
//            response.write("route222222\n");

            // 5 秒后调用下一个处理器
//            routingContext.vertx().setTimer(1000, tid ->  routingContext.next());

            //传递参数到下一个上下文中
            routingContext.put("aa", "123");
            routingContext.next();
        });
        router.route("/some/path/").handler(context -> {
            String aa = context.get("aa");
            context.request().response();
            context.response().putHeader("Content-Length", String.valueOf(aa.getBytes().length)).end(aa);
        });

//        router.route("/some/path/:id").produces("application/json;charset=utf-8").handler(routingContext -> {
//
//            HttpServerResponse response = routingContext.response();
//            String id = routingContext.request().getParam("id");
//            System.out.println(id);
//            // 结束响应
//            String encode = Json.encode(new User("李四", "难"));
//            response.putHeader("Content-Length", String.valueOf(encode.getBytes().length));
//            response.putHeader("Content-Type", "application/json;charset=utf-8");
//            response.write(encode).end();
//        });
        router.routeWithRegex(".*do").handler(r -> r.response().putHeader("Content-Type", "application/json;charset=utf-8").end("*.do"));
        router.get("/some/*").failureHandler(ctx ->
        {
            System.out.println("failure");
            ctx.response().setStatusCode(404).end("there may be a page not found please ensure the path");
        });

        server.requestHandler(router::accept).listen(8080);

    }


    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(MVertx01.class.getName());
    }
}
