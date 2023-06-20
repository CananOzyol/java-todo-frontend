package com.example.backend.models;

public enum Status {
    OPEN("OPEN"),
    DOING("DOING"),
    DONE("DONE");

    private final String message;

    Status(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
}
