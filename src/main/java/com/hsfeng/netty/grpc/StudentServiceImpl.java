package com.hsfeng.netty.grpc;

import com.hsfeng.netty.proto.MyRequest;
import com.hsfeng.netty.proto.MyResponse;
import com.hsfeng.netty.proto.StudentServiceGrpc;
import io.grpc.stub.StreamObserver;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {


    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端信息：" + request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());
        responseObserver.onCompleted();
    }
}
