package com.svem_system_email_handle_service.listener;

import com.alibaba.fastjson.JSON;
import com.svem_system_email_handle_service.bean.OrderFailGenerateRequest;
import com.svem_system_email_handle_service.business.BizOrderFailGeneratePushService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;



/**
 * 订单生成时没有缓存订单数据导致生成订单失败时消息发送
 */
@Slf4j
@Component
public class OrderFailGeneratePushListener {

    @Resource
    private BizOrderFailGeneratePushService bizOrderFailGeneratePushService;

    @KafkaListener(topics = {"topic-order-fail-generate-email-push"})
    public void execute(ConsumerRecord<?, ?> record, Acknowledgment ack) {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("topic: ").append(record.topic()).append("\n");
        sb.append("key  : ").append(record.key()).append("\n");
        sb.append("value: ").append(record.value().toString()).append("\n");

        log.info(sb.toString());

        OrderFailGenerateRequest request = null;
        try {
            request = JSON.parseObject(record.value().toString(), OrderFailGenerateRequest.class);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        if (request == null) {
            ack.acknowledge();
            return;
        }

        try {
            bizOrderFailGeneratePushService.execute(request);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        ack.acknowledge();
    }
}
