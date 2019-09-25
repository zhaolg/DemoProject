package com.example.demo.java8.interfacetest.consumer;

import java.util.function.Function;

/**
 * @author lingang.zhao
 * @version 1.0
 * @date 2019-08-15 17:38
 */
public class FunctionTest {

    public static void main(String[] args) {
        Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String val) {
                return Integer.valueOf(val);
            }
        };
        System.out.println(function.apply("1"));
    }


}
