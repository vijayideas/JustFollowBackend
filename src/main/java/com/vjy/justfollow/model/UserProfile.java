package com.vjy.justfollow.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "UserProfile")
public class UserProfile implements Serializable {

    private static final long serialVersionUID = -5048239448394661430L;
}
