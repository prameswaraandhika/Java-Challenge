package com.prameswaradev.NotificationEmailService.model;


import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ProductMail(
        String to,
        String subject,
        String body
) {
}
