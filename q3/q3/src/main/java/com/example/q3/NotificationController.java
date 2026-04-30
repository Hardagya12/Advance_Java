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

    // Combined Route showing all three
    @GetMapping("/send")
    public String sendNotification(@RequestParam(defaultValue = "Test") String msg) {
        StringBuilder output = new StringBuilder();
        
        output.append("Demonstrating Dependency Injection in Spring Boot:\n\n");
        output.append("1. ").append(fieldService.sendEmail(msg)).append("\n");
        output.append("2. ").append(constructorService.sendEmail(msg)).append("\n");
        output.append("3. ").append(setterService.sendEmail(msg)).append("\n");
        
        return output.toString();
    }

    // Individual Route for Field Injection
    @GetMapping("/send/field")
    public String sendField(@RequestParam(defaultValue = "Test") String msg) {
        return fieldService.sendEmail(msg);
    }

    // Individual Route for Constructor Injection
    @GetMapping("/send/constructor")
    public String sendConstructor(@RequestParam(defaultValue = "Test") String msg) {
        return constructorService.sendEmail(msg);
    }

    // Individual Route for Setter Injection
    @GetMapping("/send/setter")
    public String sendSetter(@RequestParam(defaultValue = "Test") String msg) {
        return setterService.sendEmail(msg);
    }
}
