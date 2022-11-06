package com.dan.cosmosnotification.dto;

import lombok.Data;

@Data
public class PasswordRecoveryDTO {
    private String username;
    private String email;
    private String passwordRecoveryToken;

}
