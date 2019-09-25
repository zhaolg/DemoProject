package com.example.demo.java8.interfacetest.consumer;

import java.util.function.Supplier;

/**
 * @author lingang.zhao
 * @version 1.0
 * @date 2019-08-15 17:10
 */
public class SupplierTest {

    public static void main(String[] args) {

        Supplier<String> supplier = new Supplier() {
            @Override
            public String get() {
                return "test Supplier";
            }
        };

        System.out.println(supplier.get());

        Supplier<String> supplier1 = () -> "test Supplier 2 ";
        System.out.println(supplier1.get());




    }


}
