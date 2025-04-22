package com.svem_system_email_handle_service.business;

import com.svem_system_email_handle_service.bean.OrderFailGenerateRequest;

import javax.mail.MessagingException;

public interface BizOrderFailGeneratePushService {
    void execute(OrderFailGenerateRequest request) throws MessagingException;
}
