package com.hyl.core.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestTask {

    @Scheduled(cron = "*/5 * * * * ?")
    public void test1() {
        System.out.println("测试1开始");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试1结束");
    }

    @Scheduled(cron = "*/5 * * * * ?")
    public void test2() {
        System.out.println("测试2开始");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试2结束");
    }
}
