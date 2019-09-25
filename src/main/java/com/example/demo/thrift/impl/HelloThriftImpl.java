package com.example.demo.thrift.impl;

import com.example.demo.thrift.Hello;
import org.apache.thrift.TException;

public class HelloThriftImpl implements Hello.Iface {

    @Override
    public String sayHello(String name) throws TException {
        System.out.println(name);
        return "success";
    }
}
