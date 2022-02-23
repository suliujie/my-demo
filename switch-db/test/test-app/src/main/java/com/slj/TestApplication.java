package com.slj;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@EnableDubbo
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.slj",exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
public class TestApplication {

  public static void main(String[] args) {
    SpringApplication.run(TestApplication.class, args);

    }

  @Bean
  public TaskScheduler taskScheduler() {
    ThreadPoolTaskScheduler taskExecutor = new ThreadPoolTaskScheduler();
    taskExecutor.setPoolSize(50);
    return taskExecutor;
  }

}
