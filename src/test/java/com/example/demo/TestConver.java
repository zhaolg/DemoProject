package com.example.demo;

import com.example.demo.redis.PECode;

/**
 * @author lingang.zhao
 * @version 1.0
 * @date 2019/9/25 1:58 下午
 */
public class TestConver {

    public static void main(String[] args) {

        String keyPrefix = "01:201155:";
        long businessId = 100545953888L;
        for (int i = 0; i < 20; i++) {
            long def = businessId + i;
            System.out.println("原始数据：" + def);
            String encode = PECode.encode(def);
            System.out.println("64进制转换后：-》：" + keyPrefix + encode);
            Long decode = PECode.decode(encode);
            System.out.println(decode.longValue());
        }
    }
}
