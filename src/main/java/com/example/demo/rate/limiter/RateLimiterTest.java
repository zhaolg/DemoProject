package com.example.demo.rate.limiter;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalTime;

/**
 * @author lingang.zhao
 * @version 1.0
 * @date 2019-08-27 19:33
 */
public class RateLimiterTest {

    public static void main(String[] args) {
        //初始化 每秒1个令牌
        RateLimiter rateLimiter = RateLimiter.create(1);
        int a = 10;
        while (true) {
            //每次需要2个立牌
            rateLimiter.acquire(1);
            LocalTime now = LocalTime.now();
            System.out.println("模拟调用三方微服务，当前时间为：" + now.toString());
            a--;
            if (a == 0) {
                break;
            }
        }
    }


}
