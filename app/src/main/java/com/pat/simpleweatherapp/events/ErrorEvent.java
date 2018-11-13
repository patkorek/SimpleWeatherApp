package com.pat.simpleweatherapp.events;

public class ErrorEvent {

    private final String errorMsg;

    public ErrorEvent(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
