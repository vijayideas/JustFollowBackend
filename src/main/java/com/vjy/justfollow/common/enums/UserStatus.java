package com.vjy.justfollow.common.enums;

public enum UserStatus {

    DELETED(0),
    ACTIVE(1),
    NOT_VERIFIED(2);
    private int status  = 0;

    UserStatus(int status) {
        this.status = status;
        // TODO Auto-generated constructor stub
    }



    public int getStatus() {
        return status;
    }
}
