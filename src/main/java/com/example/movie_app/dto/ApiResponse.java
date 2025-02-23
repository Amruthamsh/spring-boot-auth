package com.example.movie_app.dto;

import java.util.Date;

public class ApiResponse<T> {
    private final Integer status;
    private final String message;
    private final T payload;
    private final boolean success;
    private final String timestamp;

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getPayload() {
        return payload;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public ApiResponse(Integer status, String message, T payload) {
        this.status = status;
        this.message = message;
        this.payload = payload;
        this.success = status < 400;
        this.timestamp = new Date().toString();
    }
}
