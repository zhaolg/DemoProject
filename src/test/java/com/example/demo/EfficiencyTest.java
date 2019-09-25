package com.example.demo;

import com.example.demo.redis.PECode;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author lingang.zhao
 * @version 1.0
 * @date 2019/9/25 1:58 下午
 */
public class EfficiencyTest {

    public static void main(String[] args) {

        String keyPrefix = "01:201155:";
        long business = 10054595993888L;
        long startTimes = System.currentTimeMillis();
        List<String> rtn = Lists.newArrayList();
        for (int i = 0; i < 1000001; i++) {
            long def = business + i;
            rtn.add(PECode.encode(def));
        }
        long cost = System.currentTimeMillis() - startTimes;
        System.out.println("encode cost:" + cost);

        long startTimes2 = System.currentTimeMillis();
        rtn.forEach(s -> {
            long decode = PECode.decode(s);
        });
        long decodeCost = System.currentTimeMillis() - startTimes2;
        System.out.println("decode cost:" + decodeCost);
    }

}
