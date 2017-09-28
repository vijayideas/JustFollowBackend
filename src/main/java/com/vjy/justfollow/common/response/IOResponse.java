package com.vjy.justfollow.common.response;

public class IOResponse {

    private String errorMessage;
    private Status status;
    private Object data;


    public enum Status {
        SUCCESS,
        FAIELD
    }


    public IOResponse(Status status, String errorMessage, Object data) {
        this.errorMessage = errorMessage;
        this.status = status;
        this.data = data;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public Status getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }
}
