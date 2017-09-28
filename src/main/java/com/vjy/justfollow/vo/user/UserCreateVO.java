package com.vjy.justfollow.vo.user;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;


public class UserCreateVO {

    @NotEmpty
    private String name ;


    @NotEmpty
    private String mailId;

    @NotEmpty
    private String mobileNumber;

    private String picFileName;

    private String password;
    private String userId ;
    private String fbId;
    private String dateOfBirth;
    private int status ;

    public UserCreateVO() {
    }

    public UserCreateVO(String name, String mailId, String mobileNumber, String password, String userId, String fbId, String picFileName, String dateOfBirth) {
        super();

        this.name = name;
        this.mailId = mailId;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.userId = userId;
        this.fbId = fbId;
        this.picFileName = picFileName;
        this.dateOfBirth = dateOfBirth;
    }



    public UserCreateVO(String name, String mailId, String mobileNumber, String password, String userId, String picFileName, String dateOfBirth) {
        super();

        this.name = name;
        this.mailId = mailId;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.userId = userId;
        this.picFileName = picFileName;
        this.dateOfBirth = dateOfBirth;
    }


    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getPicFileName() {
        return picFileName;
    }

    public void setPicFileName(String picFileName) {
        this.picFileName = picFileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
