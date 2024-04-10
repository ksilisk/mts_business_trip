package com.hackathon.mts.tripemployeedirectory.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
class ExceptionDetails {
    private final long timestamp;
    private final int statusCode;
    private final String message;
    private final String details;
}
