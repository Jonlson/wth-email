package com.svem_system_email_handle_service.business.impl;



import com.svem_system_email_handle_service.bean.OrderFailGenerateRequest;
import com.svem_system_email_handle_service.business.BizOrderFailGeneratePushService;
import com.svem_system_email_handle_service.common.AppProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * 发送生成订单异常情况：
 */
@Service
public class BizOrderFailGeneratePushServiceImpl implements BizOrderFailGeneratePushService {

    @Resource
    private WthBankWithdrawTransactionService mthBankWithdrawTransactionService;
    @Resource
    private WthService wthService;

    @Resource
    private AppProperties appProperties;
    @Resource
    private JavaMailSender mailSender;

    @Override
    public void execute(OrderFailGenerateRequest request) throws MessagingException {

        // 构造发送模板:
        System.setProperty("mail.mime.splitlongparameters", "false");
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setFrom(appProperties.getMail().getFromMail());
        helper.setTo(appProperties.getMail().getToMail().split(","));
        helper.setSubject("订单生成失败");
        //邮箱内容组装
        StringBuffer sb = new StringBuffer();
            helper.setSubject("用户订单生成失败（" + ("订单编号：" + request.getOrderId() + "(Code)：" + (StringUtils.isNotBlank(request.getCode()) ? request.getCode() : "未知")) + ")");
            sb.append("<!DOCTYPE>"
                    + "<div bgcolor='#f1fcfa'   style='border:1px solid #d9f4ee; font-size:14px; line-height:22px; color:#005aa0;padding-left:1px;padding-top:5px;   padding-bottom:5px;'>"
                    + "</div>");

        helper.setText(sb.toString(), true);
        mailSender.send(message);
    }
}
