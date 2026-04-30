package com.example.q3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetterInjectedNotificationService {

    private EmailService emailService;

    // 3. Setter Injection
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public String sendEmail(String msg) {
        return "Setter Injection -> " + emailService.sendEmail(msg);
    }
}
