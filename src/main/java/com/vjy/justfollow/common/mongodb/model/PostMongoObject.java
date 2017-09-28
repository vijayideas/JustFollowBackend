package com.vjy.justfollow.common.mongodb.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Document(collection="posts")
public class PostMongoObject {

    @Id
    private String id;
    @NotNull
    private String userId;

    private String text;
    private String mediaType;
    private String mediaFile;
    private String privacy;

    private int likes;

    private Date createdDate;

    private List<String> likerList;

    public PostMongoObject() {
    }

    public PostMongoObject(String userId, String text, String mediaType, String mediaFile, String privacy, Date createdDate) {
        super();

        this.userId = userId;
        this.text = text;
        this.mediaType = mediaType;
        this.mediaFile = mediaFile;
        this.privacy = privacy;
        this.createdDate = createdDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public PostMongoObject(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaFile() {
        return mediaFile;
    }

    public void setMediaFile(String mediaFile) {
        this.mediaFile = mediaFile;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<String> getLikerList() {
        return likerList;
    }

    public void setLikerList(List<String > likerList) {
        this.likerList = likerList;
    }
}
