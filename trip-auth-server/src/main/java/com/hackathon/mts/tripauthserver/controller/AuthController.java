package com.hackathon.mts.tripauthserver.controller;

import com.hackathon.mts.tripauthserver.dto.AuthInfo;
import com.hackathon.mts.tripauthserver.payload.AuthResponse;
import com.hackathon.mts.tripauthserver.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping
    public AuthResponse auth(@RequestBody @Valid AuthInfo authInfo) {
        return authService.authorize(authInfo);
    }
}
