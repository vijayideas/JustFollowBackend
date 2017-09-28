package com.vjy.justfollow.vo.user;

import com.vjy.justfollow.common.utils.JustFollowUtils;

import java.util.Date;

public class UserDetailVo {


    private String id;
    private String name ;
    private String mailId;
    private String mobileNumber;
    private String userId ;
    private String fbId ;
    private String picUrl;
    private Date dateOfBirth;


    public UserDetailVo() {
    }

    public UserDetailVo(String id, String name, String mailId, String mobileNumber, String userId, String fbId, String picUrl, Date dateOfBirth) {

        this.id = id;
        this.name = name;
        this.mailId = mailId;
        this.mobileNumber = mobileNumber;
        this.userId = userId;
        this.fbId = fbId;
        this.picUrl = picUrl != null ?
                JustFollowUtils.getFileUrl(picUrl) : null;
        this.dateOfBirth = dateOfBirth;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl != null ? JustFollowUtils.getFileUrl(picUrl) : null;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
