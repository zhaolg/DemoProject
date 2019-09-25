package com.example.demo.thrift.server;

import com.example.demo.thrift.Hello;
import com.example.demo.thrift.impl.HelloThriftImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;

public class HelloThriftServer {

    private static int port = 8888;

    public static void main(String[] args) {
        try {
            //设置服务端的端口
            TServerSocket serverTransport = new TServerSocket(port);
            // 设置协议工厂为 TBinaryProtocol.Factory
            TBinaryProtocol.Factory proFactory = new TBinaryProtocol.Factory();
            // 关联处理器与 Hello 服务的实现
            TProcessor processor = new Hello.Processor(new HelloThriftImpl());

            TThreadPoolServer.Args args1 = new TThreadPoolServer.Args(serverTransport);
            args1.inputProtocolFactory(proFactory);
            args1.processor(processor);
            TServer server = new TThreadPoolServer(args1);
            System.out.println("Start server on port " + port);
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
