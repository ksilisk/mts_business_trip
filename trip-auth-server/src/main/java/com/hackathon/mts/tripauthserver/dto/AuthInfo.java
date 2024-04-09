package com.hackathon.mts.tripauthserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthInfo {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
