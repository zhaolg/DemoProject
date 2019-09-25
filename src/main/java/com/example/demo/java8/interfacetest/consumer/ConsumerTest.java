package com.example.demo.java8.interfacetest.consumer;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author lingang.zhao
 * @version 1.0
 * @date 2019-08-15 16:20
 */
public class ConsumerTest {

    public static void main(String[] args) {

        Consumer consumer = new Consumer() {
            @Override
            public void accept(Object o) {
                System.out.println("consumer val=" + o);
            }
        };

        List<String> lists = Lists.newArrayList();
        lists.add("test-1");
        lists.add("test-2");
        lists.add("test-3");
        lists.stream().forEach(consumer);


        Consumer<String> consumer1 = (val) -> System.out.println(val);
        lists.stream().forEach(consumer1);


        Consumer<String> consumer2 =  System.out::println;
        lists.stream().forEach(consumer1);

    }


}
