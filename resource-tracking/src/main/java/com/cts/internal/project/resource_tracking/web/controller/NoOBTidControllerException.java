package com.cts.internal.project.resource_tracking.web.controller;

public class NoOBTidControllerException extends RuntimeException {
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NoOBTidControllerException(String msg) {
        super(msg);
    }
}
