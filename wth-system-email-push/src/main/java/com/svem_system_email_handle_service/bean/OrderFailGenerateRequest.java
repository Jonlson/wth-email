package com.svem_system_email_handle_service.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class OrderFailGenerateRequest {
    @JSONField(name = "order_id")
    private String orderId;

    @JSONField(name = "device_code")
    private String deviceCode;
}
