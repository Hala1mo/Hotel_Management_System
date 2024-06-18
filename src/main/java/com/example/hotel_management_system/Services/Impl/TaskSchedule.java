package com.example.hotel_management_system.Services.Impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.TaskScheduler;

@Configuration
public class TaskSchedule{

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        // Configure other properties of taskScheduler if needed
        taskScheduler.setPoolSize(10); // Example: set the pool size
        return taskScheduler;
    }
}
