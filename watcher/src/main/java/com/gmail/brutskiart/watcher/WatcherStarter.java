package com.gmail.brutskiart.watcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(value = "com.gmail.brutskiart.watcher.repository.feign")
public class WatcherStarter {

    public static void main(String[] args) {
        SpringApplication.run(WatcherStarter.class, args);
    }
}
