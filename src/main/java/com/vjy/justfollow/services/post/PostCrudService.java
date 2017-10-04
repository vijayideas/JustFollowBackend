package com.vjy.justfollow.services.post;

import com.vjy.justfollow.common.response.CommonResponse;
import com.vjy.justfollow.vo.post.PostCreateVO;

public interface PostCrudService {

    CommonResponse createPost(PostCreateVO postCreateVO);

    CommonResponse deletePost(String postId);

    CommonResponse likePost(String postId, String likerId);

    CommonResponse getAllPost(String userId);

    CommonResponse deleteAll();
}
