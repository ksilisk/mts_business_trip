package com.hackathon.mts.tripauthserver.payload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AuthResponse {
    private final String username;
    private final AuthStatus status;

    public enum AuthStatus {
        ACCESS_ACCEPTED,
        ACCESS_DENIED
    }
}
