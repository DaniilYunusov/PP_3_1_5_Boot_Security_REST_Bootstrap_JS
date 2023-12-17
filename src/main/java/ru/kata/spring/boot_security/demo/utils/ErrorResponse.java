package ru.kata.spring.boot_security.demo.utils;

public class ErrorResponse {
    private String errorMessage;
    private long timespan;

    public ErrorResponse(String errorMessage, long timespan) {
        this.errorMessage = errorMessage;
        this.timespan = timespan;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public long getTimespan() {
        return timespan;
    }

    public void setTimespan(long timespan) {
        this.timespan = timespan;
    }
}
