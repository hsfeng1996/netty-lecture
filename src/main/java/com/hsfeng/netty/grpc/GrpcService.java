package com.hsfeng.netty.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcService {

    private Server server;

    private void start() throws IOException {
        this.server = ServerBuilder.forPort(8899).addService(new StudentServiceImpl()).build().start();

        System.out.println("server started");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("关闭jvm");
            GrpcService.this.stop();
        }));

        System.out.println("执行到这里");
    }

    private void stop() {
        if (null != this.server) {
            this.server.shutdown();
        }
    }

    private void awaitTermination() throws InterruptedException {
        if (null != this.server) {
            this.server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception {
        GrpcService service = new GrpcService();

        service.start();
        service.awaitTermination();
    }
}
