package com.vjy.justfollow.services.common;

import com.vjy.justfollow.common.response.CommonResponse;

public interface PostService {

    CommonResponse getAllPost(String userId);
    CommonResponse doLike(String postId, String likerId);
    CommonResponse doDislike(String postId, String likerId);
}
