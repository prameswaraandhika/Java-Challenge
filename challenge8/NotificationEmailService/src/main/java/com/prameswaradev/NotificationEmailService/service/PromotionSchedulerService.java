package com.prameswaradev.NotificationEmailService.service;

import com.prameswaradev.NotificationEmailService.model.ProductMail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Slf4j
@Service
public class PromotionSchedulerService {

    @Autowired
    private JavaMailSender mailSender;

    @Scheduled(cron = "0 0 12 * * ?") // Executes at 12:00 PM every day
    public void sendPromoNotification() {
        // Check if the current time is near mealtime (adjust the timing as needed)
        LocalTime currentTime = LocalTime.now();
        LocalTime mealStartTime = LocalTime.of(11, 0); // Adjust based on your mealtime
        LocalTime mealEndTime = LocalTime.of(13, 0);   // Adjust based on your mealtime

        if (currentTime.isAfter(mealStartTime) && currentTime.isBefore(mealEndTime)) {
            // Send the promotional notification
            sendSimpleEmail(ProductMail.builder()
                            .to("prameswaara@gmail.com")
                            .subject("Promo Discount")
                            .body("Enjoy our special promo!")
                            .build());
        }
    }

    private void sendSimpleEmail(ProductMail productMail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("prameswaara@gmail.com");
        message.setTo(productMail.to());
        message.setText(productMail.body());
        message.setSubject(productMail.subject());
        mailSender.send(message);
    }
}
