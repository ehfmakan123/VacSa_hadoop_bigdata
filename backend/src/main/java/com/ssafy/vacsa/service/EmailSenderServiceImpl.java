package com.ssafy.vacsa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService{

    private final JavaMailSender javaMailSender;

    // JavaMailSender로 이메일을 전송하는 메서드
    @Override
    @Async
    public void sendEmail(SimpleMailMessage email) throws Exception {
        javaMailSender.send(email);
    }
}
