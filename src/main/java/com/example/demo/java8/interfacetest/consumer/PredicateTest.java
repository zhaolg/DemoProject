package com.example.demo.java8.interfacetest.consumer;

import java.util.function.Predicate;

/**
 * @author lingang.zhao
 * @version 1.0
 * @date 2019-08-15 17:25
 */
public class PredicateTest {

    public static void main(String[] args) {
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer o) {
                return 1 < 2;
            }
        };

        System.out.println(predicate.test(1));

        Predicate<Integer> predicate1 = (val) -> val > 1;
        System.out.println(predicate1.test(1));

    }


}
