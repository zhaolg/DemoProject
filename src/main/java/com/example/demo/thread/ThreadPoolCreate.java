package com.example.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author lingang.zhao
 * @version 1.0
 * @date 2019/10/8 1:59 下午
 */
@Slf4j
public class ThreadPoolCreate {


    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                log.error("FutureTask exec ");
                return null;
            }
        }));

        Thread.sleep(10000L);
    }


}
