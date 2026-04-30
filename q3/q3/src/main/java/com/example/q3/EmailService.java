package com.example.q3;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public String sendEmail(String msg) {
        return "Email Sent with message: " + msg;
    }
}
