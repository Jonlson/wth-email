package com.svem_system_email_handle_service.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private Mail mail;

    @Data
    public static class Mail {
        private String fromMail;
        private String toMail;
        private String deviceMail;
        private String simMail;
    }
}
