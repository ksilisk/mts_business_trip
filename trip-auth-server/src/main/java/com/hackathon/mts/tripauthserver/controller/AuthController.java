package com.hackathon.mts.tripauthserver.controller;

import com.hackathon.mts.tripauthserver.dto.AuthInfo;
import com.hackathon.mts.tripauthserver.payload.AuthResponse;
import com.hackathon.mts.tripauthserver.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Authenticate user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully authenticated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
    @PostMapping
    public AuthResponse auth(@RequestBody @Valid AuthInfo authInfo) {
        return authService.authorize(authInfo);
    }

    @Operation(summary = "Check service status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service is OK",
                    content = {@Content(mediaType = "text/plain")}),
            @ApiResponse(responseCode = "500", description = "Error processing request",
                    content = @Content)
    })
    @GetMapping
    public String isOk() {
        return "imok";
    }
}
