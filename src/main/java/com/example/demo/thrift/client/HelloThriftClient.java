package com.example.demo.thrift.client;

import com.example.demo.thrift.Hello;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class HelloThriftClient {

    private static int port = 8888;
    public static void main(String[] args) {

        try {
            // 设置调用的服务地址为本地，端口为 7911
            TTransport transport = new TSocket("localhost", port);
            transport.open();
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(transport);
            Hello.Client client = new Hello.Client(protocol);
            // 调用服务的 helloVoid 方法
            String helloRtn = client.sayHello("hello world");
            System.out.println(helloRtn);
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
