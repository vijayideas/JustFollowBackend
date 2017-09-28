package com.vjy.justfollow.services.common.impl;

import com.vjy.justfollow.common.mongodb.model.PostMongoObject;
import com.vjy.justfollow.common.mongodb.repository.PostRepository;
import com.vjy.justfollow.common.response.CommonResponse;
import com.vjy.justfollow.services.common.PostService;
import com.vjy.justfollow.services.user.UserCrudService;
import com.vjy.justfollow.vo.post.PostDetailsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserCrudService userCrudService;

    @Override
    public CommonResponse getAllPost() {


        List<PostMongoObject> postMongoObjects = postRepository.findAllByOrderByCreatedDateDesc();
        List<PostDetailsVo> postDetailsVos = new ArrayList<>();

        for (PostMongoObject object:postMongoObjects) {

            postDetailsVos.add(new PostDetailsVo(object, userCrudService.getUserDetail(object.getUserId())));
        }


        return new CommonResponse("success", 0, postDetailsVos);
    }

    @Override
    public CommonResponse doLike(String postId, String likerId) {
        PostMongoObject  post = postRepository.findOne(postId);
        if (post.getLikerList() == null) {
            post.setLikerList(new ArrayList<>());
        }
        if (post.getLikerList() != null && !post.getLikerList().contains(likerId)) {
            post.setLikes(post.getLikes()+ 1);
            post.getLikerList().add(likerId);
            postRepository.save(post);

            return new CommonResponse("success",0, post);
        }else {
            return new CommonResponse("failed",1, post);
        }

    }

    @Override
    public CommonResponse doDislike(String postId, String likerId) {
        PostMongoObject  post = postRepository.findOne(postId);
        if (post.getLikerList() != null && post.getLikerList().contains(likerId)) {
            post.setLikes(post.getLikes()- 1);
            post.getLikerList().remove(likerId);
            postRepository.save(post);

            return new CommonResponse("success",0, post);
        }else {
            return new CommonResponse("failed",1, post);
        }
    }


}
