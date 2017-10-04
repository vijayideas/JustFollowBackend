package com.vjy.justfollow.vo.post;

import com.vjy.justfollow.common.mongodb.model.PostMongoObject;
import com.vjy.justfollow.common.mongodb.model.UserMongoObject;
import com.vjy.justfollow.common.utils.JustFollowUtils;
import com.vjy.justfollow.vo.user.UserDetailVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDetailsVo{

    private String id;
    private String userId;
    private String userName;
    private String profilePic;

    private String text;
    private String mediaType;
    private String mediaFile;
    private String privacy;

    private int likes;

    private Date createdDate;

    private List<String> likerList;

    private boolean isLiked;


    public PostDetailsVo() {
    }

    public PostDetailsVo(String id, String userId, String text, String mediaType, String mediaFile, String privacy, int likes, Date createdDate, List<String> likerList) {
        super();
        this.id = id;
        this.userId = userId;
        this.text = text;
        this.mediaType = mediaType;
        this.mediaFile = mediaFile;
        this.privacy = privacy;
        this.likes = likes;
        this.createdDate = createdDate;
        this.likerList = likerList;

    }

    public PostDetailsVo(PostMongoObject object, UserDetailVo user, String clientUserId) {
        super();

        this.id = object.getId();
        this.userId = object.getUserId();
        this.userName = user.getName();
        this.profilePic = user.getPicUrl();
        this.text = object.getText();
        this.mediaType = object.getMediaType();
        this.mediaFile = object.getMediaFile() != null ?
                JustFollowUtils.getPostFileUrl(object.getUserId(), object.getMediaFile()) : null;
        this.privacy = object.getPrivacy();
        this.likes = object.getLikes();
        this.createdDate = object.getCreatedDate();
        this.likerList = object.getLikerList() != null ? object.getLikerList() : new ArrayList<>();
        this.isLiked = object.getLikerList() != null && object.getLikerList().contains(clientUserId);
    }


    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<String> getLikerList() {
        return likerList;
    }

    public void setLikerList(List<String> likerList) {
        this.likerList = likerList;
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
}
