package com.vjy.justfollow.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Date;

@Document(collection="users")
public class User {

    @Id
    private String id;
    private String firstName ;
    private String lastName;
    private String mailId;
    private String mobileNumber;
    private String password;
    private String userId ;

    //Facebook , Google , Or Native
    private String accountCreatedFrom;

    //Created :2, Verified:3 , Is Active :1, Deleted : 0
    private int status ;
    private Date createdDate;
    private Date modifiedDate;


    public User() {
    }

    public User(String firstName, String lastName, String mailId, String mobileNumber, String userId, String accountCreatedFrom, int status, Date createdDate, Date modifiedDate) {
        super();


        this.firstName = firstName;
        this.lastName = lastName;
        this.mailId = mailId;
        this.mobileNumber = mobileNumber;
        this.userId = userId;
        this.accountCreatedFrom = accountCreatedFrom;
        this.status = status;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getAccountCreatedFrom() {
        return accountCreatedFrom;
    }

    public void setAccountCreatedFrom(String accountCreatedFrom) {
        this.accountCreatedFrom = accountCreatedFrom;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
