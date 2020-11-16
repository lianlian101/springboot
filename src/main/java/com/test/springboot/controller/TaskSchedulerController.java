package com.test.springboot.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 动态的添加定时任务
 */
@Controller
@RequestMapping("/timer/")
public class TaskSchedulerController {
    
private static final Map<String, ScheduledFuture<?>> futures = new HashMap<>();
    
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
       return new ThreadPoolTaskScheduler();
    }

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler; 

    @RequestMapping("startCron")
    @ResponseBody
    public String startCron() throws Exception {
        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(new Demo(), new CronTrigger("0/2 * * * * *"));
        futures.put("sc", future);
        return "startCron";
    }
    
    @RequestMapping("startCron2")
    @ResponseBody
    public String startCron2() throws Exception {
       ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(new Demo(), new CronTrigger("0/5 * * * * *"));
       futures.put("sc2", future);
       return "startCron2";
    }
    
    @RequestMapping("stopCron")
    @ResponseBody
    public String stopCron(String name){
        ScheduledFuture<?> future = futures.get(name);
        if(future != null){
            future.cancel(true);
        }
        return "stopCron";
    }

}

class Demo implements Runnable{

    @Override
    public void run() {
        System.out.println("定时任务开始了：" + LocalDateTime.now());
    }
    
}
