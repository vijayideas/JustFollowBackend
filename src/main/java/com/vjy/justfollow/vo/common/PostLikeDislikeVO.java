package com.vjy.justfollow.vo.common;

public class PostLikeDislikeVO {

    private String fbAccessToken;
    private String postId;


    public PostLikeDislikeVO() {
    }

    public PostLikeDislikeVO(String fbAccessToken, String postId) {
        super();
        this.fbAccessToken = fbAccessToken;
        this.postId = postId;
    }


    public String getFbAccessToken() {
        return fbAccessToken;
    }

    public void setFbAccessToken(String fbAccessToken) {
        this.fbAccessToken = fbAccessToken;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
