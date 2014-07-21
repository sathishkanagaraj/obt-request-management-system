package com.cts.internal.project.resource_tracking.web.controller;

public class NoIdExistControllerException extends RuntimeException {
    public NoIdExistControllerException(String message) {
        super(message);
    }
}
