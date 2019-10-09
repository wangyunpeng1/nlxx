package com.xx.listen;

import com.aliyuncs.exceptions.ClientException;
import com.xx.util.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues="sms")
public class SmsListen {
    @Autowired
    private SmsUtil smsUtil;
    @Value("${aliyun.sms.templateCode}")
    private String templateCode;
    @Value("${aliyun.sms.signName}")
    private String signName;
    @RabbitHandler
    public void sendSms(Map<String,String> map){
        String phone = map.get("phone") ;
        String smsCode = map.get("smsCode") ;
        String smscodeJsonStr = "{\"smsCode\":\""+smsCode+"\"}" ;
        System.out.println("手机号:"+phone+"/n验证码:"+smsCode);
        try {
            smsUtil.sendSms(phone, templateCode, signName, smscodeJsonStr);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}

