package com.vjy.justfollow.vo.post;

public class PostCreateVO {

    private String userId;

    private String fbAccessToken;

    private String text;
    private String mediaType;
    private String mediaFile;
    private String privacy;


    public PostCreateVO() {
    }


    public PostCreateVO(String fbAccessToken, String userId, String text, String mediaType, String mediaFile, String privacy) {
        super();
        this.fbAccessToken = fbAccessToken;
        this.userId = userId;
        this.text = text;
        this.mediaType = mediaType;
        this.mediaFile = mediaFile;
        this.privacy = privacy;
    }



    public PostCreateVO(String userId, String text, String mediaType, String mediaFile, String privacy) {
        super();
        this.userId = userId;
        this.text = text;
        this.mediaType = mediaType;
        this.mediaFile = mediaFile;
        this.privacy = privacy;
    }


    public String getFbAccessToken() {
        return fbAccessToken;
    }

    public void setFbAccessToken(String fbAccessToken) {
        this.fbAccessToken = fbAccessToken;
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
}
