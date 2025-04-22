package com.svem_system_email_handle_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.svem_system_email_handle_service"})
public class SystemEmailHandleServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemEmailHandleServiceApplication.class, args);
    }
}
