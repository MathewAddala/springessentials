package com.skill9.exp9;

import java.time.LocalDateTime;

public class ApiErrorResponse {

    private final LocalDateTime timestamp;
    private final String message;
    private final int statusCode;

    public ApiErrorResponse(LocalDateTime timestamp, String message, int statusCode) {
        this.timestamp = timestamp;
        this.message = message;
        this.statusCode = statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
