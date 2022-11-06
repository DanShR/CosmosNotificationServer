package com.dan.cosmosnotification.dto;

import lombok.Data;

@Data
public class SignUpDTO {
    private String username;
    private String email;
    private String emailConfirmUUID;
}
