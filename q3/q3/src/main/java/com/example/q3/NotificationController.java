package com.example.q3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private FieldInjectedNotificationService fieldService;

    @Autowired
    private ConstructorInjectedNotificationService constructorService;

    @Autowired
    private SetterInjectedNotificationService setterService;

    @GetMapping("/send")
    public String sendNotification(@RequestParam(defaultValue = "Test") String msg) {
        StringBuilder output = new StringBuilder();
        
        output.append("Demonstrating Dependency Injection in Spring Boot:\n\n");
        output.append("1. ").append(fieldService.sendEmail(msg)).append("\n");
        output.append("2. ").append(constructorService.sendEmail(msg)).append("\n");
        output.append("3. ").append(setterService.sendEmail(msg)).append("\n");
        
        return output.toString();
    }
}
