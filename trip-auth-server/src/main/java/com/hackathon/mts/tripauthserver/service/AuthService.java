package com.hackathon.mts.tripauthserver.service;

import com.hackathon.mts.tripauthserver.dto.AuthInfo;
import com.hackathon.mts.tripauthserver.payload.AuthResponse;

public interface AuthService {
    AuthResponse authorize(AuthInfo authInfo);
}
