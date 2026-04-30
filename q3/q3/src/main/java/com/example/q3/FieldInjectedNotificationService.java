package com.example.q3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldInjectedNotificationService {

    // 1. Field Injection
    @Autowired
    private EmailService emailService;

    public String sendEmail(String msg) {
        return "Field Injection -> " + emailService.sendEmail(msg);
    }
}
