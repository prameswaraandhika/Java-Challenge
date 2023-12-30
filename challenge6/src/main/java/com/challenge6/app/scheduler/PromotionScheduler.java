package com.challenge6.app.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PromotionScheduler {

    @Scheduled(cron = "30 * * * * ?")
    public void testSchedulerTask(){
        System.out.println("Email has sent to user");
    }

    @Scheduled(cron = "0 25 21 * * ?")
    public void performSchedulerTask(){
        System.out.println("Email has sent to user");
    }
}
