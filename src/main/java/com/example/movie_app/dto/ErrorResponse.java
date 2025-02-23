package com.example.movie_app.dto;

import java.util.Date;

public class ErrorResponse {
    private final Integer status;
    private final String message;
    private final String timestamp;


    public ErrorResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date().toString();
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp(){
        return timestamp;
    }
}
