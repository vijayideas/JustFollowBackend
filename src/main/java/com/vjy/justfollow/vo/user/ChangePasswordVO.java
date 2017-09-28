package com.vjy.justfollow.vo.user;

import org.hibernate.validator.constraints.NotEmpty;

public class ChangePasswordVO {

    @NotEmpty
    private String userId ;

    @NotEmpty
    private String oldPassword ;

    @NotEmpty
    private String newPassword ;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
