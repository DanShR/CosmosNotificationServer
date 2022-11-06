package com.dan.cosmosnotification.receiver;

import com.dan.cosmosnotification.dto.PasswordRecoveryDTO;
import com.dan.cosmosnotification.dto.SignUpDTO;
import com.dan.cosmosnotification.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "${queue.notification.signup}")
public class SignUpReceiver {

    private final EmailService emailService;

    public SignUpReceiver(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitHandler
    public void receive(Object message) throws JsonProcessingException {
        final String body = new String(((Message) message).getBody());
        ObjectMapper mapper = new ObjectMapper();
        final SignUpDTO signUpDTO = mapper.readValue(body, SignUpDTO.class);
        emailService.sendConfirmEmail(signUpDTO);
    }
}
