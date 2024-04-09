package com.hackathon.mts.tripauthserver.service.impl;

import com.hackathon.mts.tripauthserver.dto.AuthInfo;
import com.hackathon.mts.tripauthserver.entity.User;
import com.hackathon.mts.tripauthserver.payload.AuthResponse;
import com.hackathon.mts.tripauthserver.repository.UserRepository;
import com.hackathon.mts.tripauthserver.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.hackathon.mts.tripauthserver.payload.AuthResponse.AuthStatus.ACCESS_ACCEPTED;
import static com.hackathon.mts.tripauthserver.payload.AuthResponse.AuthStatus.ACCESS_DENIED;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;

    @Override
    public AuthResponse authorize(AuthInfo authInfo) {
        Optional<User> mayBeUser = userRepository.findUserByUsername(authInfo.getUsername());
        if (mayBeUser.isEmpty()) {
            log.info("User: {} not found.", authInfo.getUsername());
            return new AuthResponse(authInfo.getUsername(), ACCESS_DENIED);
        }
        User user = mayBeUser.get();
        if (passwordEncoder.matches(authInfo.getPassword(), user.getPassword())) {
            return new AuthResponse(authInfo.getUsername(), ACCESS_ACCEPTED);
        }
        return new AuthResponse(authInfo.getUsername(), ACCESS_DENIED);
    }
}
