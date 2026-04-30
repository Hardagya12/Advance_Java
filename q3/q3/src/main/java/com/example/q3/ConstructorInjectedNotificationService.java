package com.example.q3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstructorInjectedNotificationService {

    private final EmailService emailService;

    // 2. Constructor Injection
    @Autowired
    public ConstructorInjectedNotificationService(EmailService emailService) {
        this.emailService = emailService;
    }

    public String sendEmail(String msg) {
        return "Constructor Injection -> " + emailService.sendEmail(msg);
    }
}
