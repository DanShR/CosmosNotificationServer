package com.dan.cosmosnotification.service;

import com.dan.cosmosnotification.dto.PasswordRecoveryDTO;
import com.dan.cosmosnotification.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSenderImpl mailSender;
    private final String baseUrl;

    public EmailService(JavaMailSenderImpl mailSender, @Value("${BASE_URL}") String baseUrl) {
        this.mailSender = mailSender;
        this.baseUrl = baseUrl;
    }

    public void sendPasswordRecoveryEmail(PasswordRecoveryDTO passwordRecoveryDTO) {
        String recoveryUrl = String.format("%spasswordreset/%s", baseUrl, passwordRecoveryDTO.getPasswordRecoveryToken());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("cosmosapp");
        message.setTo(passwordRecoveryDTO.getEmail());
        message.setSubject("Password recovery");
        message.setText(String.format("Hello, %s! To recover your password follow the link  %s", passwordRecoveryDTO.getUsername(), recoveryUrl));
        mailSender.send(message);
    }

    public void sendConfirmEmail(SignUpDTO signUpDTO) {
        String confirmUrl = String.format("%sconfirmemail?token=%s", baseUrl, signUpDTO.getEmailConfirmUUID());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("cosmosapp");
        message.setTo(signUpDTO.getEmail());
        message.setSubject("Email confirmation");
        message.setText(String.format("Hello, %s! Please, confirm your email %s", signUpDTO.getUsername(), confirmUrl));
        mailSender.send(message);
    }
}
