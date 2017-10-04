package com.vjy.justfollow.services.post.impl;

import com.vjy.justfollow.common.mongodb.model.PostMongoObject;
import com.vjy.justfollow.common.mongodb.repository.PostRepository;
import com.vjy.justfollow.common.response.CommonResponse;
import com.vjy.justfollow.services.post.PostCrudService;
import com.vjy.justfollow.services.user.UserCrudService;
import com.vjy.justfollow.vo.post.PostCreateVO;
import com.vjy.justfollow.vo.post.PostDetailsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostCrudServiceImpl implements PostCrudService{


    @Autowired
    private PostRepository postRepository;


    @Autowired
    private UserCrudService userCrudService;

    @Override
    public CommonResponse createPost(PostCreateVO postCreateVO) {
        PostMongoObject mongoObject = new PostMongoObject(postCreateVO.getUserId(), postCreateVO.getText(), postCreateVO.getMediaType(),
                postCreateVO.getMediaFile(), postCreateVO.getPrivacy(), new Timestamp(System.currentTimeMillis()));

        return new CommonResponse("Post created successfully", 0, postRepository.save(mongoObject));
    }

    @Override
    public CommonResponse deletePost(String postId) {

        postRepository.delete(postId);

        return new CommonResponse("success", 0, postId);
    }

    @Override
    public CommonResponse likePost(String postId, String likerId) {
        return null;
    }

    @Override
    public CommonResponse getAllPost(String userId) {

        List<PostMongoObject> postMongoObjects = postRepository.findAllByOrderByCreatedDateDesc();
        List<PostDetailsVo> postDetailsVos = new ArrayList<>();

        for (PostMongoObject object:postMongoObjects) {
            postDetailsVos.add(new PostDetailsVo(object, userCrudService.getUserDetail(object.getUserId()), userId));
        }



        return new CommonResponse("success", 0, postDetailsVos);
    }

    @Override
    public CommonResponse deleteAll() {
        postRepository.deleteAll();

        return new CommonResponse("success", 0, null);
    }
}
